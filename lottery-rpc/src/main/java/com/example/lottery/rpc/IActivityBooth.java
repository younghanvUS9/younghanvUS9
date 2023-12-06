package com.example.lottery.rpc;

import com.example.lottery.rpc.req.ActivityReq;
import com.example.lottery.rpc.res.ActivityRes;

/**
 * 类描述
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/13 16:43
 */
public interface IActivityBooth {

    ActivityRes queryActivityById(ActivityReq req);

}

