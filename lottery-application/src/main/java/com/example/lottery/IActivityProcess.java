package com.example.lottery;

import com.example.lottery.req.DrawProcessReq;
import com.example.lottery.res.DrawProcessResult;

/**
 * 类描述 启动类
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/14 10:05
 */
public interface IActivityProcess {

    /**
     * 执行抽奖流程
     * @param req 抽奖请求
     * @return    抽奖结果
     */
    DrawProcessResult doDrawProcess(DrawProcessReq req);

}

