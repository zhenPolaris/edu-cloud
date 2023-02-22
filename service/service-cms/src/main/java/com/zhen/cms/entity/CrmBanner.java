package com.zhen.cms.entity;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhen
 * @since 2023-02-14 14:08:31
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("crm_banner")
public class CrmBanner  {
    //ID    
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    //标题
    private String title;
    //图片地址
    private String imageUrl;
    //链接地址
    private String linkUrl;
    //排序
    private Integer sort;
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

