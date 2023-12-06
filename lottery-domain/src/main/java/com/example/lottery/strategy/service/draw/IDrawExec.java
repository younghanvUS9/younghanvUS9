package com.example.lottery.strategy.service.draw;

import com.example.lottery.strategy.model.req.DrawReq;
import com.example.lottery.strategy.model.res.DrawResult;

/**
 * 类描述
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/13 17:50
 */
public interface IDrawExec {
    DrawResult doDrawExec(DrawReq req);
}

