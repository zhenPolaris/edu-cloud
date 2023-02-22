package com.zhen.edu.controller;

import com.zhen.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: zhen
 * @Create: 2023-02-07 14:14
 * @Description:
 */
@Api(description = "后台登录管理")
@RestController
@CrossOrigin
@RequestMapping("/eduService/user")
public class EduLoginController {


    @PostMapping("/login")
    public R login(){
        return R.ok().data("token","admin");
    }

    @GetMapping("/info")
    public R info(){
            return R.ok().data("roles","admin").data("name","zhen").data("avatar","http://www.weixintouxiang.cn/weixin/20140607090832328.gif");
    }
}
