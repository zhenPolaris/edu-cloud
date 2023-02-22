package com.zhen.statistics.client;

import com.zhen.commonutils.R;
import com.zhen.statistics.client.impl.UcenterClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: zhen
 * @Create: 2023-02-20 9:37
 * @Description:
 */
@Component
@FeignClient(value = "service-ucenter",fallback = UcenterClientImpl.class)
public interface UcenterClient {

    //根据日期获取注册人数
    @GetMapping("/serviceUcenter/ucenter-member/countRegister/{day}")
    public R countRegister(@PathVariable("day") String day);

}
