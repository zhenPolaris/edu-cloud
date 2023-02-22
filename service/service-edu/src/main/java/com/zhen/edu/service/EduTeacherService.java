package com.zhen.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhen.edu.entity.EduTeacher;
import com.zhen.edu.entity.dto.TeacherQueryDTO;

import java.util.Map;


/**
 * 讲师(EduTeacher)表服务接口
 *
 * @author makejava
 * @since 2023-02-04 15:20:09
 */
public interface EduTeacherService extends IService<EduTeacher> {
    void pageQuery(Page<EduTeacher> pageParam, TeacherQueryDTO teacherQueryDTO);


    Map<String, Object> getTeacherFrontPageList(Page<EduTeacher> teacherPage);
}

