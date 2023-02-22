package com.zhen.edu.entity;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhen
 * @since 2023-02-09 16:53:31
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("edu_chapter")
public class EduChapter  {
    //章节ID    
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    //课程ID
    private String courseId;
    //章节名称
    private String title;
    //显示排序
    private Integer sort;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;



}

