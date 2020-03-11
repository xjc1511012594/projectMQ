package com.mq.workapi.serializer.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mq.workapi.serializer.Serializer;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author xulei
 * @date 2020-3-10 17:34
 */
@Slf4j
public class JacksonSerializer implements Serializer {

    private static final ObjectMapper mapper=new ObjectMapper();

    static {
        mapper.disable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        mapper.configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
        mapper.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    }

    private final JavaType type;

    public JacksonSerializer(JavaType type) {
        this.type = mapper.getTypeFactory().constructType(type);
    }

    public  static JacksonSerializer createParametricType(Class<?> clz){
        return new JacksonSerializer(mapper.getTypeFactory().constructType(clz));
    }



    //对象转字节数据
    @Override
    public byte[] serializeRaw(Object data) {
        try {
            return mapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            log.error("序列化出错",e);
        }
        return null;
    }
    //对象转字符串
    @Override
    public String serialize(Object data) {
        try {
            return mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            log.error("序列化出错",e);
        }
        return null;
    }
    //字符串转对象
    @Override
    public <T> T deserialize(String content) {
        try {
            return mapper.readValue(content,type);
        } catch (IOException e) {
            log.error("序列化出错",e);
        }
        return null;
    }
    //字节数组转对象
    @Override
    public <T> T deserialize(byte[] content) {
        try {
            return mapper.readValue(content,type);
        } catch (IOException e) {
            log.error("序列化出错",e);
        }
        return null;
    }
}
