package com.example.lottery.award.service.factory;

import com.example.lottery.award.service.IDistributionGoods;
import org.springframework.stereotype.Service;

/**
 * 类描述
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/13 18:11
 */
@Service
public class DistributionGoodsFactory extends GoodsConfig{
    public IDistributionGoods getDistributionGoodsService(Integer awardType){
        return goodsMap.get(awardType);
    }

}
