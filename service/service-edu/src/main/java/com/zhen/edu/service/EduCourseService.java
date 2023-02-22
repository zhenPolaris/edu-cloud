package com.zhen.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhen.edu.entity.EduCourse;
import com.zhen.edu.entity.vo.CourseFrontVo;
import com.zhen.edu.entity.vo.CourseInfoForm;
import com.zhen.edu.entity.vo.CoursePublishVo;
import com.zhen.edu.entity.vo.CourseWebVo;

import java.util.Map;

/**
 * @author zhen
 * @since 2023-02-09 16:50:26
 */
public interface EduCourseService extends IService<EduCourse> {

    //添加课程基本信息方法
    String saveCourseInfo(CourseInfoForm courseInfoForm);

    //根据课程id查询课程信息
    CourseInfoForm getCourseInfo(String courseId);

    //修改课程信息
    void updateCourseInfo(CourseInfoForm courseInfoForm);

    //根据课程id查询课程确认信息
    public CoursePublishVo getPublishCourseInfo(String courseId);

    boolean removeCourse(String courseId);

    //前台多条件分页查询
    Map<String, Object> getCourseFrontInfo(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}

