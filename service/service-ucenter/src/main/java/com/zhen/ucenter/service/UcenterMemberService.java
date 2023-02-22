package com.zhen.ucenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhen.ucenter.entity.UcenterMember;
import com.zhen.ucenter.entity.vo.LoginVo;
import com.zhen.ucenter.entity.vo.RegisterVo;

/**
 * @author zhen
 * @since 2023-02-16 08:53:58
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);

    UcenterMember getMemberByOpenId(String openid);

    Integer getCountRegister(String day);
}

