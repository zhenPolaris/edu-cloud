package com.zhen.ucenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhen.ucenter.entity.UcenterMember;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhen
 * @since 2023-02-16 08:53:57
 */
/**
 * 会员表(UcenterMember)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-16 08:53:57
 */
@Mapper
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    Integer getCountRegister(String day);
}

