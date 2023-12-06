package com.example.lottery.rpc.res;

import com.example.lottery.common.Result;
import com.example.lottery.rpc.dto.ActivityDto;

import java.io.Serializable;

/**
 * 抽奖结果
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/13 16:42
 */
public class ActivityRes implements Serializable {
    private Result result;
    private ActivityDto activity;

    public ActivityRes(Result result){
        this.result = result;
    }

    public ActivityRes(Result result, ActivityDto activity) {
        this.result = result;
        this.activity = activity;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public ActivityDto getActivity() {
        return activity;
    }

    public void setActivity(ActivityDto activity) {
        this.activity = activity;
    }



}
