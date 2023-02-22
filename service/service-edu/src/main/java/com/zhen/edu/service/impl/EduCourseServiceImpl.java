package com.zhen.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhen.commonutils.ResultCode;
import com.zhen.edu.entity.EduCourse;
import com.zhen.edu.entity.EduCourseDescription;
import com.zhen.edu.entity.vo.CourseFrontVo;
import com.zhen.edu.entity.vo.CourseInfoForm;
import com.zhen.edu.entity.vo.CoursePublishVo;
import com.zhen.edu.entity.vo.CourseWebVo;
import com.zhen.edu.mapper.EduCourseMapper;
import com.zhen.edu.service.EduChapterService;
import com.zhen.edu.service.EduCourseDescriptionService;
import com.zhen.edu.service.EduCourseService;
import com.zhen.edu.service.EduVideoService;
import com.zhen.servicebase.exception.SystemException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhen
 * @since 2023-02-09 16:50:26
 */
@Service("eduCourseService")
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    private EduCourseMapper eduCourseMapper;
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduChapterService eduChapterService;


    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {

        if (courseInfoForm.getCover() == null){
            
        }
        //向课程表添加课程基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        int insert = baseMapper.insert(eduCourse);

        if(insert <= 0 ){
            throw new SystemException(ResultCode.ADD_ERROR.getCode(),ResultCode.ADD_ERROR.getMessage());
        }

        //获取添加之后课程信息的id
        String cid = eduCourse.getId();

        //向课程简介表里添加课程简介
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoForm,eduCourseDescription);

        eduCourseDescription.setId(cid);

        boolean save = eduCourseDescriptionService.save(eduCourseDescription);
        if (!save){
            throw new SystemException(ResultCode.ADD_ERROR.getCode(),ResultCode.ADD_ERROR.getMessage());
        }

        return cid;

    }

    @Override
    public CourseInfoForm getCourseInfo(String courseId) {
        //查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(eduCourse,courseInfoForm);
        //查询简介表
        EduCourseDescription description = eduCourseDescriptionService.getById(courseId);
        courseInfoForm.setDescription(description.getDescription());

        return courseInfoForm;
    }

    @Override
    public void updateCourseInfo(CourseInfoForm courseInfoForm) {

            //1、修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);

        int update = baseMapper.updateById(eduCourse);
        if (update <= 0){
                throw new SystemException(ResultCode.CHANGE_ERROR.getCode(),ResultCode.CHANGE_ERROR.getMessage());
            }

            //2、修改描述信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoForm.getDescription());
        eduCourseDescription.setId(courseInfoForm.getId());
        boolean b = eduCourseDescriptionService.updateById(eduCourseDescription);
        if (!b){
            throw new SystemException(ResultCode.CHANGE_ERROR.getCode(),ResultCode.CHANGE_ERROR.getMessage());
        }

    }

    @Override
    public CoursePublishVo getPublishCourseInfo(String courseId) {
        return eduCourseMapper.getPublishCourseInfo(courseId);
    }

    //删除课程
    @Override
    public boolean removeCourse(String id) {

        //1、根据课程id删除小节
        eduVideoService.removeVideoByCourseId(id);

        //2、根据课程id删除章节部分
        eduChapterService.deleteChapter(id);

        //3、根据课程id删除课程描述
        eduCourseDescriptionService.removeById(id);

        //4、根据课程id删除课程本身
        int result = baseMapper.deleteById(id);

        if (result>=1){
            return true;
        }else {
            throw new SystemException(20001,"删除失败");
        }


    }


    //前台多条件分页查询
    @Override
    public Map<String, Object> getCourseFrontInfo(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo) {

        String title = null;
        String subjectId = null;
        String subjectParentId = null;
        String gmtCreateSort = null;
        String buyCountSort = null;
        String priceSort = null;
        String teacherId = null;

        if (!StringUtils.isEmpty(courseFrontVo)){
            title = courseFrontVo.getTitle();
            subjectId = courseFrontVo.getSubjectId();
            subjectParentId = courseFrontVo.getSubjectParentId();
            gmtCreateSort = courseFrontVo.getGmtCreateSort();
            buyCountSort = courseFrontVo.getBuyCountSort();
            priceSort = courseFrontVo.getPriceSort();
            teacherId = courseFrontVo.getTeacherId();
        }


        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        //判断条件值是否为空，不为空拼接条件
        if (!StringUtils.isEmpty(subjectParentId)){//一级分类
            wrapper.eq("subject_parent_id",subjectParentId);
        }
        if (!StringUtils.isEmpty(subjectId)){//二级分类
            wrapper.eq("subject_id",subjectId);
        }
        if (!StringUtils.isEmpty(buyCountSort)){//关注度
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(priceSort)){//价格
            wrapper.orderByDesc("price");
        }
        if (!StringUtils.isEmpty(gmtCreateSort)){//最新，创建时间
            wrapper.orderByDesc("gmt_create");
        }


        baseMapper.selectPage(pageCourse, wrapper);

        long total = pageCourse.getTotal();//总记录数
        List<EduCourse> courseList = pageCourse.getRecords();//数据集合
        long size = pageCourse.getSize();//每页记录数
        long current = pageCourse.getCurrent();//当前页
        long pages = pageCourse.getPages();//总页数
        boolean hasPrevious = pageCourse.hasPrevious();//是否有上一页
        boolean hasNext = pageCourse.hasNext();//是否有下一页

        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("list",courseList);
        map.put("size",size);
        map.put("current",current);
        map.put("pages",pages);
        map.put("hasPrevious",hasPrevious);
        map.put("hasNext",hasNext);

        return map;
    }

    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {
        return baseMapper.getBaseCourseInfo(courseId);
    }


}


