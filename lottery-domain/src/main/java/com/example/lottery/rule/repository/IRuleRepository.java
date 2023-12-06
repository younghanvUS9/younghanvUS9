package com.example.lottery.rule.repository;

import com.example.lottery.rule.model.aggregates.TreeRuleRich;

/**
 * 类描述
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/13 18:00
 */
public interface IRuleRepository {
    TreeRuleRich queryTreeRuleRich(Long treeId);
}

