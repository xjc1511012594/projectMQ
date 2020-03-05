package com.mq.workapi;

/**
 * @author xulei
 * @date 2020-3-4 17:44
 */
public final class MessageType {

    /**
     *   迅速消息：不需要保障消息的可靠性， 也不需要做confirm确认
     * */
    public static final String PAPID="0";

    /**
     *  确认消息：不需要保障消息的可靠性，但是会做消息的confirm确认
     * */
    public static final String CONFIRM="1";

    /**
     *  可靠性消息：一定要保障消息的100%可靠性投递，不允许有任何消息的丢失
     *  PS:
     * */
     public static final String RELIANT="2";

}
