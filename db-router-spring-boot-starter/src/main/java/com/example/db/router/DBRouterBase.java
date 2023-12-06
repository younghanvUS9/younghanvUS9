package com.example.db.router;

/**
 * 数据源基础配置
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/14 10:37
 */
public class DBRouterBase {

    private String tbIdx;

    public String getTbIdx() {
        return DBContextHolder.getTBKey();
    }

}
