package com.zhen.edu.entity;


import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * 讲师(EduTeacher)表实体类
 *
 * @author makejava
 * @since 2023-02-04 15:20:08
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("edu_teacher")
public class EduTeacher  {
    //讲师ID    
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    //讲师姓名
    private String name;
    //讲师简介
    private String intro;
    //讲师资历,一句话说明讲师
    private String career;
    //头衔 1高级讲师 2首席讲师
    private Integer level;
    //讲师头像
    private String avatar;
    //排序
    private Integer sort;
    //逻辑删除 1（true）已删除， 0（false）未删除
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer isDeleted;
    //创建时间
    @ApiModelProperty(value = "创建时间",example = "2019-01-01 8:00:00")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    //更新时间
    @ApiModelProperty(value = "更新时间",example = "2019-01-01 8:00:00")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}


