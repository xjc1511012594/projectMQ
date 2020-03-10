package com.mq.workapi.serializer.impl;

import com.mq.workapi.serializer.Serializer;
import com.mq.workapi.serializer.SerializerFactory;

/**
 * @author xulei
 * @date 2020-3-10 17:35
 */
public class JacksonSerializerFactory implements SerializerFactory {

    public static final SerializerFactory INSTANCE=new JacksonSerializerFactory();

    @Override
    public Serializer create() {
        return null;
    }
}
