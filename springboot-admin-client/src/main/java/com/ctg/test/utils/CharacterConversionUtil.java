package com.ctg.test.utils;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.json.simple.JSONArray;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CharacterConversionUtil {
    /** 转换为驼峰（大写）
     * 
     * @param underscoreName
     * @return */
    public static String camelCaseName(String underscoreName) {
        StringBuilder result = new StringBuilder();
        if (underscoreName != null && underscoreName.length() > 0) {
            boolean flag = false;
            for (int i = 0; i < underscoreName.length(); i++) {
                char ch = underscoreName.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                }
                else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    }
                    else {
                        result.append(Character.toLowerCase(ch));
                    }
                }
            }
        }
        return result.toString();
    }

    /** 转换为下划线(大写)
     * 
     * @param camelCaseName
     * @return */
    public static String underscoreName(String camelCaseName) {
        StringBuilder result = new StringBuilder();
        int len = camelCaseName.length();
        if (camelCaseName != null && len > 0) {
            result.append(camelCaseName.substring(0, 1).toUpperCase());
            for (int i = 1; i < len; i++) {
                char ch = camelCaseName.charAt(i);
                if (Character.isUpperCase(ch)) {
                    result.append("_");
                    result.append(ch);
                }
                else {
                    result.append(Character.toUpperCase(ch));
                }
            }
        }
        return result.toString();
    }
    
    /**
     * 适用场景: 转换String类型的json串，的 key为下划线大写
     * 调用方式: 
     * 业务逻辑说明
    */
    public final static Object convertToUpperUnderline(String json) {
        Object obj = JSON.parse(json);
        convertToUpperUnderline(obj);
        return obj;
    }

    /**
     * 适用场景: 递归转换json key为下划线大写
     * 调用方式: 
     * 业务逻辑说明
    */
    private final static void convertToUpperUnderline(Object json) {
        if (json instanceof JSONArray) {
            JSONArray arr = (JSONArray) json;
            for (Object obj : arr) {
                convertToUpperUnderline(obj);
            }
        } else if (json instanceof JSONObject) {
            JSONObject jo = (JSONObject) json;
            Set<String> keys = jo.keySet();
            String[] array = keys.toArray(new String[keys.size()]);
            for (String key : array) {
            	Pattern pattern = Pattern.compile("^[A-Z0-9_]*$");
            	Matcher matcher = pattern.matcher(key);
                Object value = jo.get(key);
              //如果key已经全部大写，就不再转化
            	if(!matcher.matches()){
            		String replace = key.replace("_", "");
                    jo.remove(key);
                    jo.put(underscoreName(replace), value);
            	}
                    convertToUpperUnderline(value);
            }
        }
    }
    
    
    /**
     * 适用场景: 递归转换json key为驼峰
     * 调用方式: 
     * 业务逻辑说明
    */
    private final static void convertToCamel(Object json) {
        if (json instanceof JSONArray) {
            JSONArray arr = (JSONArray) json;
            for (Object obj : arr) {
                convertToCamel(obj);
            }
        } else if (json instanceof JSONObject) {
            JSONObject jo = (JSONObject) json;
            Set<String> keys = jo.keySet();
            String[] array = keys.toArray(new String[keys.size()]);
            for (String key : array) {
                Object value = jo.get(key);
                String camelCaseName = camelCaseName(key);
                jo.remove(key);
                jo.put(camelCaseName, value);
                convertToCamel(value);
            }
        }
    }
 
    /**
     * 适用场景: 转换String类型的json串，的 key为驼峰例如：STAFF_INFO_REQ转为staffInfoReq
     * 调用方式: 
     * 业务逻辑说明
    */
    public final static Object convertToCamel(String json) {
        Object obj = JSON.parse(json);
        convertToCamel(obj);
        return obj;
    }

   /* public static void main(String[] args) {
        String a="{\"operStaff\":\"SZXX0022\",\n" +
                "\"provinceCode\":\"51\",\n" +
                "\"eparchyCode\":\"0755\",\n" +
                "\"cityCode\":\"0755\",\n" +
                "\"channelId\":\"5110053\",\n" +
                "\"channelType\":\"3000000\",\n" +
                "\"operDepartCode\":\"5110053\",\n" +
                "\"busiDataJson\":\"null\",\n" +
                "\"current\":\"1\",\n" +
                "\"pageSize\":\"10\",\n" +
                "\"offset\":\"0\",\n" +
                "\"limit\":\"10\",\n" +
                "\"batchId\":\"null\",\n" +
                "\"serialNumber\":\"null\",\n" +
                "\"status\":\"1\",\n" +
                "\"oldStatus\":\"null\",\n" +
                "\"startTime\":\"2019-05-10 00:00:00\",\n" +
                "\"endTime\":\"2019-05-10 23:59:59\",\n" +
                "\"queryStaffNo\":\"\",\n" +
                "\"batchType\":\"1\"}";
        System.out.println(convertToUpperUnderline(a));
    }*/

}
