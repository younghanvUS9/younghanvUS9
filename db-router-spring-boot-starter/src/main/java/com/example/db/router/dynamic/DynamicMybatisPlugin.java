package com.example.db.router.dynamic;

import com.example.db.router.DBContextHolder;
import com.example.db.router.annotation.DBRouterStrategy;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Mybatis 拦截器，通过对 SQL 语句的拦截处理，修改分表信息
 * Interceptor主要用途是允许你在mybatis执行期间拦截和修改其行为(即在sql语句执行前根据有没有注解来决定是否要修改sql语句中的表名)
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/14 10:46
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class DynamicMybatisPlugin implements Interceptor {

    /**
     *  创建一个用于匹配 SQL 查询语句中表名的正则表达式
     */
    private Pattern pattern = Pattern.compile("(from|into|update)[\\s]{1,}(\\w{1,})", Pattern.CASE_INSENSITIVE);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取StatementHandler
        //通过 invocation.getTarget() 方法，可以获取到该调用对象，并将其强制类型转换为 StatementHandler 对象，这样就能够获得处理当前 SQL 查询的对象。
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        //使用 MetaObject 工具类可以更加方便地访问和操作 Java 对象的属性。通过 MetaObject.forObject() 方法，将 statementHandler 对象转化为 MetaObject 对象，后面几个参数分别是要使用的对象工厂、对象包装器和反射工厂。
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        //获取到 MetaObject 对象之后，通过 metaObject.getValue() 方法获取该对象中的 mappedStatement 属性，进而获取到当前查询的 MappedStatement 对象。在 MyBatis 中，MappedStatement 是用于描述一个查询语句的元信息对象，包含了 SQL 语句、查询参数、结果映射等信息。
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");

        // 获取自定义注解判断是否进行分表操作
        //id 属性用于指定当前映射语句的唯一标识，通常格式为 “namespace.statementId”，例如 “com.example.mapper.UserMapper.getUserById”。通过调用 mappedStatement.getId() 方法获取当前 SQL 执行的 MappedStatement 对象的 ID 属性。
        String id = mappedStatement.getId();
        //根据 MappedStatement 的 ID 属性，获取到当前 SQL 执行所在的类名。例如，如果 ID 为 “com.example.mapper.UserMapper.getUserById”，则截取字符串 “com.example.mapper.UserMapper” 作为当前 SQL 所在的类名。
        String className = id.substring(0, id.lastIndexOf("."));
        //通过反射 API 加载指定的类。这里通过 Class.forName() 方法，根据当前 SQL 所在的类名 className 对应的类对象。
        Class<?> clazz = Class.forName(className);
        DBRouterStrategy dbRouterStrategy = clazz.getAnnotation(DBRouterStrategy.class);
        //判断当前类是否使用了 DBRouterStrategy 注解，并且判断分表策略 splitTable 是否设置为 true。如果二者都满足，返回 invocation.proceed() 继续执行 SQL 查询，如果不满足，则拦截 SQL 查询进入 intercept() 方式，进行自定义的处理逻辑。
        if (null == dbRouterStrategy || !dbRouterStrategy.splitTable()){
            return invocation.proceed();
        }

        // 获取SQL
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();

        // 替换SQL中的表名 USER 为 USER_03
        //使用 java.util.regex.Pattern 类创建一个针对 SQL 查询语句匹配表名的正则表达式。使用正则表达式可以更加灵活地匹配 SQL 查询语句中的表名，兼容不同的 SQL 语法格式。
        //这里通过 pattern.matcher(sql) 方法创建一个 Matcher 对象，并对 SQL 查询语句进行匹配，通过 matcher.group().trim() 方法获取到匹配的表名，多个表名会匹配到第一个表名。表名后增加 _和分表键值，用于区分不同的分表。
        Matcher matcher = pattern.matcher(sql);
        String tableName = null;
        //如果与正则表达式匹配成功
        if (matcher.find()) {
            //matcher.group() 方法返回当前匹配到的子串，也就是匹配到的表名。
            tableName = matcher.group().trim();
        }
        //断言表名不能为空，如果匹配的表名为空，会抛出异常，表名不能为空。
        assert null != tableName;
        //使用 matcher.replaceAll() 方法，将匹配到的表名替换为 tableName + "_" + DBContextHolder.getTBKey()，DBContextHolder.getTBKey() 返回的是当前线程的分表键值，用于区分分表的名称。
        String replaceSql = matcher.replaceAll(tableName + "_" + DBContextHolder.getTBKey());

        // 通过反射修改SQL语句
        Field field = boundSql.getClass().getDeclaredField("sql");
        field.setAccessible(true);
        field.set(boundSql, replaceSql);
        field.setAccessible(false);

        return invocation.proceed();
    }

}
