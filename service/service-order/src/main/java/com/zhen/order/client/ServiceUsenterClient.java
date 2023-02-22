package com.zhen.order.client;

import com.zhen.commonutils.vo.UcenterMemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: zhen
 * @Create: 2023-02-17 15:17
 * @Description:
 */
@Component
@FeignClient("service-ucenter")
public interface ServiceUsenterClient {
    //根据用户id，获取用户信息
    @GetMapping("/serviceUcenter/ucenter-member/getMemberInfoById/{memberId}")
    public UcenterMemberVo getMemberInfoById(@PathVariable("memberId") String memberId);

}
