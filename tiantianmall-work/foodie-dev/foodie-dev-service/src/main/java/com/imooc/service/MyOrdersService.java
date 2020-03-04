package com.imooc.service;

import com.imooc.pojo.Orders;

/**
 * @author xulei
 * @date 2019-12-30 18:17
 */
public interface MyOrdersService {
    Orders queryMyOrder(String userId, String orderId);
}
