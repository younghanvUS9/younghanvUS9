package com.example.db.router.dynamic;

import com.example.db.router.DBContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 AbstractRoutingDataSource是spring框架中的一个类，它提供了数据源的动态路由功能，可以让应用程序根据需要动态地选择使用哪个数据源
 * 在多数据源的应用中，通过使用AbstractRoutingDataSource，可以将不同的数据源与不同的线程或者用户进行关联。当应用程序需要访问数据库时，AbstractRoutingDataSource会根据当前线程或者用户选择对应的数据源，从而实现多数据源的动态切换
 * 使用AbstractRoutingDataSource需要继承该类，并实现determineCurrentLookupKey()方法，该方法返回当前数据源的标识。应用程序可以根据自己的需要来定义数据源标识的生成规则，例如根据当前登录用户的信息来生成数据源标识
 * 动态数据源获取，每当切换数据源，都要从这个里面进行获取
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/14 10:45
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * "db" + DBContextHolder.getDBKey() 的字符串拼接，返回的就是当前数据源的 name，例如 "db1" 或 "db2"，用于在 MyBatis 中设定数据源名称，进而实现多数据源的动态切换操作。
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return "db" + DBContextHolder.getDBKey();
    }


}
