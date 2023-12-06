package com.example.lottery.activity.service.partake;

import com.example.lottery.activity.model.req.PartakeReq;
import com.example.lottery.activity.model.vo.ActivityBillVO;
import com.example.lottery.activity.repository.IActivityRepository;

import javax.annotation.Resource;

/**
 * 类描述
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/13 18:31
 */
public class ActivityPartakeSupport {

    @Resource
    protected IActivityRepository activityRepository;

    protected ActivityBillVO queryActivityBill(PartakeReq req){

        return activityRepository.queryActivityBill(req);
    }

}
