package com.example.lottery.dao;

import com.example.lottery.po.UserStrategyExport;
import org.apache.ibatis.annotations.Mapper;

/**
 * 类描述
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/13 17:19
 */
@Mapper
public interface IUserStrategyExportDao {

    /**
     * 新增数据
     * @param userStrategyExport 用户策略
     */
    void insert(UserStrategyExport userStrategyExport);

    /**
     * 查询数据
     * @param uId 用户ID
     * @return 用户策略
     */
    UserStrategyExport queryUserStrategyExportByUId(String uId);

}

