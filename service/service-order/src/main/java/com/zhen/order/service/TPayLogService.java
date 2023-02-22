package com.zhen.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhen.order.entity.TPayLog;

import java.util.Map;

/**
 * @author zhen
 * @since 2023-02-17 14:30:25
 */
public interface TPayLogService extends IService<TPayLog> {

    Map<String, Object> createWxQrcode(String orderNo);

    Map<String, String> queryPayStatus(String orderNo);

    void updateOrderStatus(Map<String, String> map);

}

