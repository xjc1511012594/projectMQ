package com.imooc.service;

import com.imooc.pojo.OrderStatus;
import com.imooc.pojo.bo.SubmitOrderBO;
import com.imooc.pojo.vo.OrderVO;

/**
 * @author xulei
 * @date 2020-1-13 20:48
 */
public interface OrderService {
    OrderVO createOrder(SubmitOrderBO submitOrderBO) ;
    void    updateOrderStatus(String orderId, Integer orderStatus);
    OrderStatus queryOrderStatusInfo(String orderId);
    void closeOrder();
}
