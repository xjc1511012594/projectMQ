package com.mq.workapi.service;

import com.mq.workapi.constant.BrokerMessageStatus;
import com.mq.workapi.entity.BrokerMessage;
import com.mq.workapi.mapper.BrokerMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author xulei
 * @date 2020-3-13 8:57
 *   记录消息投递的情况
 *      第一次发送前  先记录到本地数据库
 *      尝试重新发送的时候  记录重试次数
 */
@Service
public class MessageStoreService {

    @Autowired
    private BrokerMessageMapper brokerMessageMapper;

    public int insert(BrokerMessage brokerMessage){
        return brokerMessageMapper.insert(brokerMessage);}

    public void success(String messageId){
        brokerMessageMapper.changeBrokerMessageStatus(messageId,
                BrokerMessageStatus.SEND_OK.getCode(),
                new Date());
    }



}
