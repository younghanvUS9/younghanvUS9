package com.example.lottery.dao;

import com.example.lottery.po.RuleTree;
import org.apache.ibatis.annotations.Mapper;

/**
 * 类描述
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/13 17:20
 */
@Mapper
public interface RuleTreeDao {

    /**
     * 规则树查询
     *
     * @param id ID
     * @return 规则树
     */
    RuleTree queryRuleTreeByTreeId(Long id);

    /**
     * 规则树简要信息查询
     *
     * @param treeId 规则树ID
     * @return 规则树
     */
    RuleTree queryTreeSummaryInfo(Long treeId);

}

