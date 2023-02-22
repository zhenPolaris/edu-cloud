package com.zhen.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhen.edu.entity.EduComment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhen
 * @since 2023-02-17 13:00:20
 */
/**
 * 评论(EduComment)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-17 13:00:20
 */
@Mapper
public interface EduCommentMapper extends BaseMapper<EduComment> {

}

