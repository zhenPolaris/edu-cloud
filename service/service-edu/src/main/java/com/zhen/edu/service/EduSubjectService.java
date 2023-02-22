package com.zhen.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhen.edu.entity.EduSubject;
import com.zhen.edu.entity.vo.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author zhen
 * @since 2023-02-09 09:50:58
 */
public interface EduSubjectService extends IService<EduSubject> {

    //添加课程分类
    void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

    List<OneSubject> getAllOneTwoSubject();

}

