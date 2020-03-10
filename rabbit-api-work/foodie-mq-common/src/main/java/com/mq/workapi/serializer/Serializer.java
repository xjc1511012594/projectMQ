package com.mq.workapi.serializer;

/**
 * @author xulei
 * @date 2020-3-10 17:16
 *      序列化和反序列的接口
 */
public interface Serializer {

    byte[] serializeRaw(Object data);

    String serialize(Object data);

    <T> T deserialize(String content);

    <T> T deserialize(byte[] content);
}
