package com.lear.api;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回code枚举类
 * @author 天狗
 */
public class MsgCode {

    protected static Map<Integer, String> map = new HashMap<>();

    public static final int MSG_CODE_SUCCESS = 0;
    public static final int MSG_CODE_UNKNOWN = -10000;

    public MsgCode() {
    }

    static {
        map.put(0, "请求成功.");
        map.put(-10000, "未知错误.");
    }

}
