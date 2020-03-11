package com.mq.workapi.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xulei
 * @date 2020-3-10 17:25
 *
 *      java对象和json互转的工具类
 */
public class FastJsonConvertUtil {

    private static final SerializerFeature[] featuresWithNullValue = { SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullBooleanAsFalse,
            SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty };


    //1.将json字符串转为实体对象
    public static <T> T jsonStringToObject(String data,Class<T> clz){
        T t = JSON.parseObject(data, clz);
        return t;
    }
    //2.将jsonObject转为实体对象
    public static <T> T jsonObjectToObject(JSONObject object,Class<T> clz){
        T t = JSON.toJavaObject(object, clz);
        return t;
    }
    //3.将json字符串数组转为实体对象集合
    public static <T> List<T> jsonStringToArray(String data,Class<T> clz){
        List<T> array = JSON.parseArray(data, clz);
        return array;
    }
    //4.将List<JsonObject>集合转为实体对象集合
    public static<T> List<T>  jsonObjectToArray(List<JSONObject> data,Class<T> clz){
        List<T> arrayList = new ArrayList<>();
        for(JSONObject obj:data){
            T t = JSON.toJavaObject(obj,clz);
            arrayList.add(t);
        }
        return arrayList;
    }
    //5.将实体对象转为json字符串
    public static String objectToJsonString(Object obj){
        String jsonString = JSON.toJSONString(obj);
        return jsonString;
    }
    //6.将实体对象转为jsonObject
    public static JSONObject ObjectToJsonObject(Object obj){
        JSONObject jsonObject = (JSONObject) JSON.toJSON(obj);
        return jsonObject;
    }

    public static String convertObjectToJSONWithNullValue(Object obj) {
        try {
            String text = JSON.toJSONString(obj, featuresWithNullValue);
            return text;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
