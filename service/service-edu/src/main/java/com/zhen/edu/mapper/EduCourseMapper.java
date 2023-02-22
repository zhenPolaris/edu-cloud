package com.zhen.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhen.edu.entity.EduCourse;
import com.zhen.edu.entity.vo.CoursePublishVo;
import com.zhen.edu.entity.vo.CourseWebVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhen
 * @since 2023-02-09 16:50:26
 */
/**
 * 课程(EduCourse)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-09 16:50:26
 */
@Mapper
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public CoursePublishVo getPublishCourseInfo(String courseId);

    CourseWebVo getBaseCourseInfo(String courseId);
}

