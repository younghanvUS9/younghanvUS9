package com.example.lottery.activity.service.partake;

import com.example.lottery.activity.model.req.PartakeReq;
import com.example.lottery.activity.model.res.PartakeResult;
import com.example.lottery.activity.model.vo.DrawOrderVO;
import com.example.lottery.common.Result;

/**
 * 类描述
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/13 18:32
 */
public interface IActivityPartake {

    /**
     * 参与活动
     * @param req 入参
     * @return    领取结果
     */
    PartakeResult doPartake(PartakeReq req);

    /**
     * 保存奖品单
     * @param drawOrder 奖品单
     * @return          保存结果
     */
    Result recordDrawOrder(DrawOrderVO drawOrder);

}

