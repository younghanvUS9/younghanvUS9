package com.example.lottery.rpc.req;

import java.io.Serializable;

/**
 * 类描述
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/14 10:13
 */
public class ActivityReq implements Serializable {
    private Long activityId;

    public Long getActivityId(){
        return this.activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }


}
