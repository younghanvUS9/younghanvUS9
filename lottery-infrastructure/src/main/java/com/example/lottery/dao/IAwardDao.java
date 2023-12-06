package com.example.lottery.dao;

import com.example.lottery.po.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 类描述
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/13 17:18
 */
@Mapper
public interface IAwardDao {

    Award queryAwardInfo(String awardId);


    /**
     * 插入奖品配置
     *
     * @param list 奖品配置
     */

    void insertList(List<Award> list);


}

