package com.example.lottery.rule.service.engine;

import com.example.lottery.rule.model.req.DecisionMatterReq;
import com.example.lottery.rule.model.res.EngineResult;

/**
 * 类描述
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/13 18:03
 */
public interface EngineFilter {
    EngineResult process(final DecisionMatterReq matter);
}
