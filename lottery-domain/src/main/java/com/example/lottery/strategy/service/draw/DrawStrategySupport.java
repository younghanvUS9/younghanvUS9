package com.example.lottery.strategy.service.draw;

import com.example.lottery.strategy.model.aggregates.StrategyRich;
import com.example.lottery.strategy.model.vo.AwardBriefVO;
import com.example.lottery.strategy.repository.IStrategyRepository;

import javax.annotation.Resource;

/**
 * 类描述
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/13 17:50
 */
public class DrawStrategySupport extends DrawConfig{
    @Resource
    protected IStrategyRepository strategyRepository;

    protected StrategyRich queryStrategyRich(Long strategyId){
        return strategyRepository.queryStrategyRich(strategyId);
    }

    /**
     * 查询奖品详情信息
     *
     * @param awardId 奖品ID
     * @return 中奖详情
     */
    protected AwardBriefVO queryAwardInfoByAwardId(String awardId){
        return strategyRepository.queryAwardInfo(awardId);
    }

}
