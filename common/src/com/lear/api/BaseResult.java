package com.lear.api;

/**
 * 返回结果基类
 * @author 天狗
 */
public class BaseResult {

    protected int msgCode;
    protected String errMsg;

    public BaseResult success() {
        this.msgCode = MsgCode.MSG_CODE_SUCCESS;
        this.errMsg = MsgCode.map.get(MsgCode.MSG_CODE_SUCCESS);
        return this;
    }

    public BaseResult fail() {
        this.msgCode = MsgCode.MSG_CODE_UNKNOWN;
        this.errMsg = MsgCode.map.get(MsgCode.MSG_CODE_UNKNOWN);
        return this;
    }

    public BaseResult fail(String msg) {
        this.msgCode = MsgCode.MSG_CODE_UNKNOWN;
        this.errMsg = msg;
        return this;
    }


    public String toResponse() {
        return "{\"msgCode\":" + msgCode + ",\"errMsg\":\"" + errMsg + "\"}";
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "msgCode=" + msgCode +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}
