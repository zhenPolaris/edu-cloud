package com.zhen.edu.entity;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhen
 * @since 2023-02-17 13:00:20
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("edu_comment")
public class EduComment  {
    //讲师ID    
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    //课程id
    private String courseId;
    //讲师id
    private String teacherId;
    //会员id
    private String memberId;
    //会员昵称
    private String nickname;
    //会员头像
    private String avatar;
    //评论内容
    private String content;
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

