package com.lear.util;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * api对象转换工具类
 * @author 天狗
 */
public class ParamsConvertUtil {

    public static <T> T params2Obj(String params, Class<T> cls) {
        String[] paramList = params.split("&");
        try {
            T obj = cls.newInstance();
            for (String param : paramList) {
                String[] kv = param.split("=");
                String key = kv[0];
                String value = kv[1];
                BeanUtils.setProperty(obj, key, value);
            }
            return obj;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String obj2Params(Object obj) {
        StringBuilder sb = new StringBuilder("?");
        Field[] fields = obj.getClass().getDeclaredFields();
        if (fields.length == 0) {
            return "";
        }
        try {
        for (int i = 0; i < fields.length-1; i++) {
            fields[i].setAccessible(true);
            if (fields[i].get(obj)==null) {
                continue;
            }
            sb.append(fields[i].getName()).append('=').append(fields[i].get(obj)).append('&');
        }
        fields[fields.length-1].setAccessible(true);
        if (fields[fields.length-1].get(obj)==null) {
            return sb.substring(0, sb.length()-1);
        }
        sb.append(fields[fields.length-1].getName()).append('=').append(fields[fields.length-1].get(obj));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }



}
