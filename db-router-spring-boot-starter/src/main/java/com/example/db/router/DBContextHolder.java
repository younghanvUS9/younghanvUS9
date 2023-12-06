package com.example.db.router;

/**
 * 数据源上下文
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/14 10:37
 */
public class DBContextHolder {

    /**
     * 一个线程对应一个数据库和分表，因此用ThreadLocal修饰。DBKey为数据库，TBKey为数据表
     */
    private static final ThreadLocal<String> dbKey = new ThreadLocal<String>();
    private static final ThreadLocal<String> tbKey = new ThreadLocal<String>();

    public static void setDBKey(String dbKeyIdx){
        dbKey.set(dbKeyIdx);
    }

    public static String getDBKey(){
        return dbKey.get();
    }

    public static void setTBKey(String tbKeyIdx){
        tbKey.set(tbKeyIdx);
    }

    public static String getTBKey(){
        return tbKey.get();
    }

    /**
     * 提供清空方法，用于回收当前线程的资源。在数据源操作完成后，应该清空当前线程内的数据源键值，以便于下一轮数据源的调用。
     */
    public static void clearDBKey(){
        dbKey.remove();
    }

    public static void clearTBKey(){
        tbKey.remove();
    }


}
