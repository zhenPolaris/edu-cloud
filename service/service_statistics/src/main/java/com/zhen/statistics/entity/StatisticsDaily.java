package com.zhen.statistics.entity;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhen
 * @since 2023-02-20 08:49:08
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("statistics_daily")
public class StatisticsDaily  {
    //主键    
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    //统计日期
    private String dateCalculated;
    //注册人数
    private Integer registerNum;
    //登录人数
    private Integer loginNum;
    //每日播放视频数
    private Integer videoViewNum;
    //每日新增课程数
    private Integer courseNum;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;



}

