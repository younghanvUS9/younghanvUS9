package com.example.lottery.award.service;

import com.example.lottery.award.model.req.GoodsReq;
import com.example.lottery.award.model.res.DistributionRes;

/**
 * 类描述
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/13 18:11
 */
public interface IDistributionGoods {

    /**
     * 奖品配送接口，奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）
     *
     * @param req   物品信息
     * @return      配送结果
     */

    DistributionRes doDistribution(GoodsReq req);

    Integer getDistributionGoodsName();
}

