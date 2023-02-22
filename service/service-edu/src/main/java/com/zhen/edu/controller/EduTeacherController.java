package com.zhen.edu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhen.commonutils.R;
import com.zhen.commonutils.SystemConstant;
import com.zhen.edu.entity.EduTeacher;
import com.zhen.edu.entity.dto.TeacherQueryDTO;
import com.zhen.edu.service.EduTeacherService;
import com.zhen.servicebase.exception.SystemException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: zhen
 * @Create: 2023-02-04 15:20
 * @Description:
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping
    public R list(){
        return R.ok().data("items",eduTeacherService.list(null));
    }

    @ApiOperation(value = "分页讲师列表")
    @PostMapping("{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page",value = "当前页码" , required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit",value = "每页记录数",required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherQueryDTO",value = "查询对象",required = false)
            @RequestBody
            TeacherQueryDTO teacherQueryDTO){
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        eduTeacherService.pageQuery(pageParam,teacherQueryDTO);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }



    @ApiOperation(value = "根据id删除讲师")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id",value = "讲师id",required = true)
            @PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping
    public R save(
            @ApiParam(name = "eduTeacher",value = "讲师对象",required = true)
            @RequestBody EduTeacher eduTeacher){
        if (eduTeacher.getAvatar() == null){
            eduTeacher.setAvatar(SystemConstant.TEACHER_AVATAR_COMMON_URL);
        }
        boolean save = eduTeacherService.save(eduTeacher);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("getById/{id}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("item",teacher);
    }

    @ApiOperation(value = "根据ID修改讲师")
    @PostMapping("updateTeacher")
    public R updateById(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher eduTeacher){
        boolean update = eduTeacherService.updateById(eduTeacher);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }

    }
}
