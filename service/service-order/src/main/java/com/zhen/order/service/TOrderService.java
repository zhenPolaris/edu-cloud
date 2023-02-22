package com.zhen.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhen.order.entity.TOrder;

/**
 * @author zhen
 * @since 2023-02-17 14:29:46
 */
public interface TOrderService extends IService<TOrder> {

    String createOrders(String courseId, String memberId);
}

