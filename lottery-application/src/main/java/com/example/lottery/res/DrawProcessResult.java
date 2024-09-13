package com.example.lottery.res;

import com.example.lottery.common.Result;
import com.example.lottery.strategy.model.vo.DrawAwardInfo;

/**
 * 类描述 响应
 *
 * @author yanghanwei
 * @version 1.0.0
 * 2023/7/14 10:06
 */
public class DrawProcessResult extends Result {

    private DrawAwardInfo drawAwardInfo;

    public DrawProcessResult(String code, String info) {
        super(code, info);
    }

    public DrawProcessResult(String code, String info, DrawAwardInfo drawAwardInfo) {
        super(code, info);
        this.drawAwardInfo = drawAwardInfo;
    }

    public DrawAwardInfo getDrawAwardInfo() {
        return drawAwardInfo;
    }

    public void setDrawAwardInfo(DrawAwardInfo drawAwardInfo) {
        this.drawAwardInfo = drawAwardInfo;
    }

}
