package com.zhen.ucenter.entity;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhen
 * @since 2023-02-16 08:53:58
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ucenter_member")
public class UcenterMember  {
    //会员id    
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    //微信openid
    private String openid;
    //手机号
    private String mobile;
    //密码
    private String password;
    //昵称
    private String nickname;
    //性别 1 女，2 男
    private Integer sex;
    //年龄
    private Integer age;
    //用户头像
    private String avatar;
    //用户签名
    private String sign;
    //是否禁用 1（true）已禁用，  0（false）未禁用
    private Boolean isDisabled;
    //逻辑删除 1（true）已删除， 0（false）未删除
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer isDeleted;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;



}

