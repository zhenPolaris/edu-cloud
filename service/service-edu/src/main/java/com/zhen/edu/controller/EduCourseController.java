package com.zhen.edu.controller;

import com.zhen.commonutils.R;
import com.zhen.commonutils.vo.EduCourseVo;
import com.zhen.edu.entity.EduCourse;
import com.zhen.edu.entity.vo.CourseInfoForm;
import com.zhen.edu.entity.vo.CoursePublishVo;
import com.zhen.edu.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.CodePointLength;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhen
 * @since 2023-02-09 16:50:26
 */
@Api(description = "课程管理")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/educourse")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @ApiOperation("添加课程信息")
    @PostMapping("/addCourseInfo")
    public R addCourse(@RequestBody CourseInfoForm courseInfoForm){
        String s = eduCourseService.saveCourseInfo(courseInfoForm);
        return R.ok().data("courseId",s);
    }

    //根据课程id查询课程基本信息
    @ApiOperation("根据课程id查询课程信息")
    @GetMapping("/getCourseInfoById/{courseId}")
    public R getCourseInfoById(@PathVariable String courseId){
        CourseInfoForm courseInfoForm = eduCourseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoForm",courseInfoForm);
    }

    //修改课程信息
    @ApiOperation("修改课程信息")
    @PostMapping("/updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoForm courseInfoForm){
        eduCourseService.updateCourseInfo(courseInfoForm);
        return R.ok();
    }

    //根据课程id查询课程确认信息
    @GetMapping("/getpublishCourseInfo/{id}")
    public R getpublishCourseInfo(@PathVariable String id){
        CoursePublishVo publishCourseInfo = eduCourseService.getPublishCourseInfo(id);
        return R.ok().data("publishCourse",publishCourseInfo);
    }

    //课程列表中删除课程方法
    @DeleteMapping("/removeCourseById/{courseId}")
    public R removeCourseById(@PathVariable("courseId") String courseId) {
        boolean flag = eduCourseService.removeCourse(courseId);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //课程最终发布
    //修改课程状态
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setStatus("Normal"); //设置课程发布状态
        eduCourse.setId(id);
        boolean flag = eduCourseService.updateById(eduCourse);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }


    //获取课程信息 => 订单调用
    @PostMapping("/getCourseInfoByIdOrder/{courseId}")
    public EduCourseVo getCourseInfoByIdOrder(@PathVariable String courseId){

        EduCourse byId = eduCourseService.getById(courseId);

        EduCourseVo eduCourseVo = new EduCourseVo();
        BeanUtils.copyProperties(byId,eduCourseVo);

        return eduCourseVo;
    }


}



