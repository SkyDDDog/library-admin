package com.lear.api;

import com.alibaba.fastjson.JSONObject;
import com.lear.util.ResultConvertUtil;

/**
 * 常用返回结果类
 * @author 天狗
 */
public class CommonResult extends BaseResult {

    private JSONObject item;

    public CommonResult() {
        item = new JSONObject();
    }

    public CommonResult success(String key, Object value) {
        super.success();
        if (null != key && null != value) {
            this.item.put(key, value);
        }

        return this;
    }

    public void putItem(String key, Object value) {
        this.item.put(key, value);
    }

    public JSONObject getItem() {
        return this.item;
    }

    public void setItem(JSONObject item) {
        this.item = item;
    }

    @Override
    public String toResponse() {
        return "{\"msgCode\":" + msgCode + ",\"errMsg\":\"" + errMsg + "\",\"item\":" + item.toJSONString() + "}";
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "errMsg='" + errMsg + '\'' +
                ", item=" + item +
                '}';
    }

}
