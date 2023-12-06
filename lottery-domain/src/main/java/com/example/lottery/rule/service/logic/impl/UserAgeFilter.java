package com.example.lottery.rule.service.logic.impl;

import com.example.lottery.rule.model.req.DecisionMatterReq;
import com.example.lottery.rule.service.logic.BaseLogic;
import org.springframework.stereotype.Component;

/**
 * 类描述
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/13 18:02
 */
@Component
public class UserAgeFilter extends BaseLogic {

    @Override
    public String matterValue(DecisionMatterReq decisionMatter) {
        return decisionMatter.getValMap().get("age").toString();
    }

}
