package com.example.lottery.po;

import java.util.Date;

/**
 * 类描述
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/13 17:12
 */
public class Award {

    // 自增ID
    private Long id;

    // 奖品ID
    private String awardId;

    // 奖品类型（文字描述、兑换码、优惠券、实物奖品暂无）
    private Integer awardType;

    // 奖品数量
    private Integer awardCount;

    // 奖品名称
    private String awardName;

    // 奖品内容「文字描述、Key、码」
    private String awardContent;

    // 创建时间
    private Date createTime;

    // 修改时间
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }

    public Integer getAwardType() {
        return awardType;
    }

    public void setAwardType(Integer awardType) {
        this.awardType = awardType;
    }

    public Integer getAwardCount() {
        return awardCount;
    }

    public void setAwardCount(Integer awardCount) {
        this.awardCount = awardCount;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getAwardContent() {
        return awardContent;
    }

    public void setAwardContent(String awardContent) {
        this.awardContent = awardContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


}
