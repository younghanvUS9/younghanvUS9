package com.example.lottery.award.service.goods.impl;

import com.example.lottery.award.model.req.GoodsReq;
import com.example.lottery.award.model.res.DistributionRes;
import com.example.lottery.award.service.IDistributionGoods;
import com.example.lottery.award.service.goods.DistributionBase;
import com.example.lottery.common.Constants;
import org.springframework.stereotype.Component;

/**
 * 类描述
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/13 18:13
 */
@Component
public class DescGoods extends DistributionBase implements IDistributionGoods {

    @Override
    public DistributionRes doDistribution(GoodsReq req) {
        super.updateUserAwardState(req.getuId(), req.getOrderId(), req.getAwardId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());

        return new DistributionRes(req.getuId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());

    }

    @Override
    public Integer getDistributionGoodsName() {
        return Constants.AwardType.DESC.getCode();
    }

}
