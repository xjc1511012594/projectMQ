package com.mq.workapi.broker;

import com.google.common.collect.Lists;
import com.mq.workapi.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xulei
 * @date 2020-3-10 11:37
 */
public class MessageHolder {

        private List<Message> messages=Lists.newArrayList();

        @SuppressWarnings({"rawtypes","unchecked"})
        public static final ThreadLocal<MessageHolder> holder=new ThreadLocal(){
            @Override
            protected Object initialValue() {
                return new MessageHolder();
            }
        };

        public static void add(Message message){holder.get().messages.add(message);}

        public static List<Message> clear(){
            List<Message> tmp = Lists.newArrayList(holder.get().messages);
            holder.remove();
            return tmp;
        }


}
