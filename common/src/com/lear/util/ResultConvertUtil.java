package com.lear.util;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * 返回结果转换类
 * str<->obj
 * @author 天狗
 */
public class ResultConvertUtil {

    public static String obj2Str(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                sb.append(field.getName()).append(": ").append(field.get(obj)).append(';');
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static <T> T str2Obj(String str, Class<T> clz) {
        String[] params = str.split(";");
        try {
            T obj = clz.newInstance();
            if (params.length == 0) {
                return obj;
            }
            for (int i = 1; i < params.length; i++) {
                String[] kv = params[i].split(": ");
                String key = kv[0];
                String value = kv[1];
                BeanUtils.setProperty(obj, key, value);
            }
            return obj;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }




    public static int getMsgCode(String str) {
        str = str.replaceAll(", errMsg:.*", "").replaceAll(".*msgCode: ", "");
        return Integer.parseInt(str);
    }

}
