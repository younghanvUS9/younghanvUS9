package com.example.db.router.config;

import com.example.db.router.DBRouterConfig;
import com.example.db.router.DBRouterJoinPoint;
import com.example.db.router.dynamic.DynamicDataSource;
import com.example.db.router.dynamic.DynamicMybatisPlugin;
import com.example.db.router.strategy.IDBRouterStrategy;
import com.example.db.router.strategy.impl.DBRouterStrategyHashCode;
import com.example.db.router.util.PropertyUtil;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置解析
 * 重写EnvironmentAware中的setEnvironment方法可以在工程启动时，获取到系统环境变量和application配置文件中的变量
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/14 10:43
 */
@Configuration
public class DataSourceAutoConfig implements EnvironmentAware {

    /**
     * 数据源配置组
     */
    private Map<String, Map<String, Object>> dataSourceMap = new HashMap<>();

    /**
     * 默认数据源配置
     */
    private Map<String, Object> defaultDataSourceConfig;

    /**
     * 分库数量
     */
    private int dbCount;

    /**
     * 分表数量
     */
    private int tbCount;

    /**
     * 路由字段
     */
    private String routerKey;


    /**
     * 注册相关切面bean
     */
    @Bean(name = "db-router-point")
    @ConditionalOnMissingBean
    public DBRouterJoinPoint point(DBRouterConfig dbRouterConfig, IDBRouterStrategy dbRouterStrategy) {
        return new DBRouterJoinPoint(dbRouterConfig, dbRouterStrategy);
    }

    /**
     * 把分库分表配置生成一个Bean对象，方便在切面类中进行注入使用
     */
    @Bean
    public DBRouterConfig dbRouterConfig() {
        return new DBRouterConfig(dbCount, tbCount, routerKey);
    }

    /**
     * 注册mybatis SQL拦截器
     */
    @Bean
    public Interceptor plugin() {
        return new DynamicMybatisPlugin();
    }

    /**
     * 创建动态数据源，这个数据源会被Mybatis SpringBoot Starter中sqlSessionFactory sqlSessionFactory(DataSource dataSource)注入使用
     */
    @Bean
    public DataSource dataSource() {
        // 创建数据源
        Map<Object, Object> targetDataSources = new HashMap<>();
        for (String dbInfo : dataSourceMap.keySet()) {
            Map<String, Object> objMap = dataSourceMap.get(dbInfo);
            targetDataSources.put(dbInfo, new DriverManagerDataSource(objMap.get("url").toString(), objMap.get("username").toString(), objMap.get("password").toString()));
        }

        // 设置数据源
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(new DriverManagerDataSource(defaultDataSourceConfig.get("url").toString(), defaultDataSourceConfig.get("username").toString(), defaultDataSourceConfig.get("password").toString()));

        return dynamicDataSource;
    }

    /**
     * 注册路由策略
     */
    @Bean
    public IDBRouterStrategy dbRouterStrategy(DBRouterConfig dbRouterConfig) {
        return new DBRouterStrategyHashCode(dbRouterConfig);
    }

    /**
     * 设置事务的传播行为
     */
    @Bean
    public TransactionTemplate transactionTemplate(DataSource dataSource) {
        //DataSourceTransactionManager对象是 Spring 中处理数据库事务的通用实现。DataSourceTransactionManager 对象将作为 TransactionTemplate 的事务管理器。
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();

        dataSourceTransactionManager.setDataSource(dataSource);

        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(dataSourceTransactionManager);

        //设置事务模板的传播行为。PROPAGATION_REQUIRED 表示在当前的事务范围内执行，如果不存在事务则创建新事务。PROPAGATION_REQUIRED 是 Spring 中最常用的事务传播行为。
        transactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRED");

        //返回创建好的 TransactionTemplate 对象，Spring 容器将会管理它，并且在需要的时候将其注入到其他组件中以使用与事务相关的方法和属性。
        return transactionTemplate;
    }

    /**
     * 提取配置文件中的注册数据源
     */
    @Override
    public void setEnvironment(Environment environment) {
        String prefix = "mini-db-router.jdbc.datasource.";

        dbCount = Integer.valueOf(environment.getProperty(prefix + "dbCount"));
        tbCount = Integer.valueOf(environment.getProperty(prefix + "tbCount"));
        routerKey = environment.getProperty(prefix + "routerKey");

        // 分库分表数据源
        String dataSources = environment.getProperty(prefix + "list");
        assert dataSources != null;

        //dbInfo代表数据库
        for (String dbInfo : dataSources.split(",")) {
            Map<String, Object> dataSourceProps = PropertyUtil.handle(environment, prefix + dbInfo, Map.class);
            dataSourceMap.put(dbInfo, dataSourceProps);
        }

        // 默认数据源
        String defaultData = environment.getProperty(prefix + "default");
        defaultDataSourceConfig = PropertyUtil.handle(environment, prefix + defaultData, Map.class);

    }


}
