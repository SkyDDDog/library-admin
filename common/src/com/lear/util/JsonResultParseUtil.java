package com.lear.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lear.entity.database.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * json对象解析工具类
 * @author 天狗
 */
public class JsonResultParseUtil {

    public static <T> List<T> parseResultList(String result, String key, Class<T> clazz) {
        JSONObject jsonResult = JSONObject.parseObject(result);
        return parseResultList(jsonResult, key, clazz);
    }

    public static <T> T parseResult(String result, String key, Class<T> clazz) {
        JSONObject jsonResult = JSONObject.parseObject(result);
        return parseResult(jsonResult, key, clazz);
    }

    public static <T> List<T> parseResultList(JSONObject jsonResult, String key, Class<T> clazz) {
        ArrayList<T> targetResult = new ArrayList<>();
        JSONArray resultList = jsonResult.getJSONObject("item").getJSONArray(key);
        for (Object result : resultList) {
            JSONObject jsonObjResult = (JSONObject) result;
            T outputResult = jsonObjResult.toJavaObject(clazz);
            targetResult.add(outputResult);
        }
        return targetResult;
    }

    public static <T> T parseResult(JSONObject jsonResult, String key, Class<T> clazz) {
        JSONObject item = jsonResult.getJSONObject("item").getJSONObject(key);
        return item.toJavaObject(clazz);
    }

}
