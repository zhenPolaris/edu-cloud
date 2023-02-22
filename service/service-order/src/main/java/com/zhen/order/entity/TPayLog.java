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
 * @since 2023-02-17 14:30:25
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_pay_log")
public class TPayLog  {
        
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    //订单号
    private String orderNo;
    //支付完成时间
    private Date payTime;
    //支付金额（分）
    private BigDecimal totalFee;
    //交易流水号
    private String transactionId;
    //交易状态
    private String tradeState;
    //支付类型（1：微信 2：支付宝）
    private Integer payType;
    //其他属性
    private String attr;
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

