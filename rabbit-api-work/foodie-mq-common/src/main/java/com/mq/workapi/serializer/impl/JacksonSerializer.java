package com.mq.workapi.serializer.impl;

import com.mq.workapi.serializer.Serializer;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author xulei
 * @date 2020-3-10 17:34
 */
@Slf4j
public class JacksonSerializer implements Serializer {

    private static final ObjectMapper mapper=new ObjectMapper();






    @Override
    public byte[] serializeRaw(Object data) {
        return new byte[0];
    }

    @Override
    public String serialize(Object data) {
        return null;
    }

    @Override
    public <T> T deserialize(String content) {
        return null;
    }

    @Override
    public <T> T deserialize(byte[] content) {
        return null;
    }
}
