package com.example.lottery.dao;

import com.example.lottery.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

/**
 * 类描述
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/13 17:18
 */
@Mapper
public interface IStrategyDao {

    /**
     * 查询
     * @param strategyId 策略id
     * @return 策略
     */
    Strategy queryStrategy(Long strategyId);

    /**
     * 插入策略配置
     *
     * @param req 策略配置
     */
    void insert(Strategy req);


}
