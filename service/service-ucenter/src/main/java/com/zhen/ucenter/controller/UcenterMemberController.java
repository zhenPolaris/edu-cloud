package com.zhen.ucenter.controller;

import com.zhen.commonutils.JwtUtils;
import com.zhen.commonutils.R;
import com.zhen.ucenter.entity.UcenterMember;
import com.zhen.ucenter.entity.vo.LoginVo;
import com.zhen.ucenter.entity.vo.RegisterVo;
import com.zhen.commonutils.vo.UcenterMemberVo;
import com.zhen.ucenter.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhen
 * @since 2023-02-16 08:53:56
 */
@RestController
@RequestMapping("/serviceUcenter/ucenter-member")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    //登录
    @PostMapping("/login")
    public R login(@RequestBody LoginVo loginVo){
        //返回token，使用jwt生成
        String token = ucenterMemberService.login(loginVo);
        return R.ok().data("token",token);
    }

    //注册
    @PostMapping("/register")
    public R register(@RequestBody RegisterVo registerVo){
        ucenterMemberService.register(registerVo);
        return R.ok();
    }

    //根据token获取用户信息
    @GetMapping("/getUserInfoForJwt")
    public R getUserInfoForJwt(HttpServletRequest request){
        //调用jwt工具类里面的根据request对象，获取头信息，返回用户id
        String id = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库，根据用户id，获取用户信息
        UcenterMember member = ucenterMemberService.getById(id);

        return R.ok().data("userInfo",member);
    }

    //根据用户id查询用户信息
    @GetMapping("/getMemberInfoById/{memberId}")
    public UcenterMemberVo getMemberInfoById(@PathVariable String memberId){
        UcenterMember member = ucenterMemberService.getById(memberId);
        UcenterMemberVo memberVo = new UcenterMemberVo();
        BeanUtils.copyProperties(member,memberVo);

        return memberVo;
    }

    @GetMapping("/countRegister/{day}")
    public R countRegister(@PathVariable String day){
        Integer count = ucenterMemberService.getCountRegister(day);
        return R.ok().data("countRegister",count);
    }

}



