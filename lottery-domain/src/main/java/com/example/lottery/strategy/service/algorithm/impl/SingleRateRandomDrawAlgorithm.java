package com.example.lottery.strategy.service.algorithm.impl;

import com.example.lottery.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.List;

/**
 * 单项随机概率抽奖，抽到一个已经排掉的奖品则未中奖
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/13 17:54
 */
@Component("singleRateRandomDrawAlgorithm")
public class SingleRateRandomDrawAlgorithm extends BaseAlgorithm {
    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        // 获取策略对应的元祖
        String[] rateTuple = super.rateTupleMap.get(strategyId);

        assert rateTuple != null;

        //随机索引
        int randomVal= new SecureRandom().nextInt(100) + 1;
        int idx = super.hashIdx(randomVal);

        String awardId = rateTuple[idx];
        if(excludeAwardIds.contains(awardId)) {
            return "未中奖";
        }

        return awardId;
    }

}
