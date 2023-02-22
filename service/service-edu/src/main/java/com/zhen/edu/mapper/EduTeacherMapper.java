package com.zhen.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhen.edu.entity.EduTeacher;
import org.apache.ibatis.annotations.Mapper;


/**
 * 讲师(EduTeacher)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-04 15:20:07
 */
@Mapper
public interface EduTeacherMapper extends BaseMapper<EduTeacher> {

}

