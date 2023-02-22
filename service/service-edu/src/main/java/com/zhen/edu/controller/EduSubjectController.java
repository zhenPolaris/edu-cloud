package com.zhen.edu.controller;

import com.zhen.commonutils.R;
import com.zhen.edu.entity.vo.OneSubject;
import com.zhen.edu.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author zhen
 * @since 2023-02-09 09:50:58
 */
@Api(description = "课程分类管理")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/edusubject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;


    @ApiOperation(value = "添加课程分类")
    @PostMapping("/addSubject")
    public R addSubject(MultipartFile file){
        eduSubjectService.saveSubject(file,eduSubjectService);
        return R.ok();
    }


    @ApiOperation("课程分类列表")
    @GetMapping("/getAllSubject")
    public R getAllSubject(){
        List<OneSubject> list = eduSubjectService.getAllOneTwoSubject();

        return R.ok().data("list", list);
    }

}



