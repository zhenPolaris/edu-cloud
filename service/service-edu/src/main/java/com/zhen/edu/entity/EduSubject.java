package com.zhen.edu.entity;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhen
 * @since 2023-02-09 09:50:58
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("edu_subject")
public class EduSubject  {
    //课程类别ID    
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    //类别名称
    @ApiModelProperty("类别名称")
    private String title;
    //父ID
    @ApiModelProperty("父id")
    private String parentId;
    //排序字段
    @ApiModelProperty("排序字段")
    private Integer sort;
    //创建时间
    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    //更新时间
    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;



}

