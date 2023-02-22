package com.zhen.order.entity;

import java.math.BigDecimal;
import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhen
 * @since 2023-02-17 14:29:46
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_order")
public class TOrder  {
        
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    //订单号
    private String orderNo;
    //课程id
    private String courseId;
    //课程名称
    private String courseTitle;
    //课程封面
    private String courseCover;
    //讲师名称
    private String teacherName;
    //会员id
    private String memberId;
    //会员昵称
    private String nickname;
    //会员手机
    private String mobile;
    //订单金额（分）
    private BigDecimal totalFee;
    //支付类型（1：微信 2：支付宝）
    private Integer payType;
    //订单状态（0：未支付 1：已支付）
    private Integer status;
    //逻辑删除 1（true）已删除， 0（false）未删除
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDeleted;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;



}

