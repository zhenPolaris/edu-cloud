package com.zhen.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhen.commonutils.JwtUtils;
import com.zhen.commonutils.R;
import com.zhen.order.entity.TOrder;
import com.zhen.order.service.TOrderService;
import com.zhen.servicebase.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhen
 * @since 2023-02-17 14:29:44
 */
@RestController
@CrossOrigin
@RequestMapping("/eduorder/t-order")
public class TOrderController {

    @Autowired
    private TOrderService tOrderService;


    //生成订单方法
    @PostMapping("/createOrder/{courseId}")
    public R createOrder(@PathVariable String courseId, HttpServletRequest request){
        //从请求头中获取用户id(解析token)
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isEmpty(memberId)){
            throw new SystemException(20001,"请登录后操作");
        }
        //生成订单，返回对应订单号
        String orderNo = tOrderService.createOrders(courseId,memberId);

        return R.ok().data("orderNo",orderNo);
    }
    //根据订单号查询订单信息
    @GetMapping("/getOrderInfoById/{orderNo}")
    public R getOrderInfoById(@PathVariable String orderNo){
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no",orderNo);
        TOrder one = tOrderService.getOne(queryWrapper);
        return R.ok().data("item",one);
    }

    //查询订单状态
    @GetMapping("isBuyCourse/{memberId}/{courseId}")
    public Boolean isBuyCourse(@PathVariable String memberId , @PathVariable String courseId){
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        queryWrapper.eq("member_id",memberId);

        TOrder one = tOrderService.getOne(queryWrapper);
        if (one.getStatus() == 1){
            return true;
        }else{
            return false;
        }
    }
}



