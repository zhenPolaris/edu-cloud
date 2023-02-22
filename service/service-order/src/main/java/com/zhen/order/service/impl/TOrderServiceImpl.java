package com.zhen.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhen.commonutils.vo.EduCourseVo;
import com.zhen.commonutils.vo.UcenterMemberVo;
import com.zhen.order.client.ServiceEduClient;
import com.zhen.order.client.ServiceUsenterClient;
import com.zhen.order.entity.TOrder;
import com.zhen.order.mapper.TOrderMapper;
import com.zhen.order.service.TOrderService;
import com.zhen.order.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhen
 * @since 2023-02-17 14:29:47
 */
@Service("tOrderService")
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    @Autowired
    private ServiceEduClient serviceEduClient;
    @Autowired
    private ServiceUsenterClient serviceUsenterClient;
    @Autowired
    private TOrderService tOrderService;

    @Override
    public String createOrders(String courseId, String memberId) {
        //获取课程信息
        EduCourseVo courseInfo = serviceEduClient.getCourseInfoByIdOrder(courseId);
        //获取用户信息
        UcenterMemberVo memberInfo = serviceUsenterClient.getMemberInfoById(memberId);


        TOrder tOrder = new TOrder();
        tOrder.setMobile(memberInfo.getMobile());
        tOrder.setNickname(memberInfo.getNickname());
        tOrder.setMemberId(memberId);
        tOrder.setCourseCover(courseInfo.getCover());
        tOrder.setCourseId(courseId);
        tOrder.setCourseTitle(courseInfo.getTitle());
        tOrder.setTeacherName(courseInfo.getTeacherName());
        tOrder.setTotalFee(courseInfo.getPrice());
        tOrder.setStatus(0);//支付状态：（ 0：已支付，1：未支付 ）
        tOrder.setPayType(1);//支付类型： 1：微信 ， 2：支付宝
        tOrder.setOrderNo(OrderNoUtil.getOrderNo()); //订单号

        baseMapper.insert(tOrder);
        return tOrder.getOrderNo();
    }


}

