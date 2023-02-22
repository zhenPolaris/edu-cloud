package com.zhen.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhen.commonutils.ResultCode;
import com.zhen.edu.entity.EduSubject;
import com.zhen.edu.entity.excel.SubjectData;
import com.zhen.edu.entity.vo.OneSubject;
import com.zhen.edu.entity.vo.TwoSubject;
import com.zhen.edu.listener.SubjectExcelListener;
import com.zhen.edu.mapper.EduSubjectMapper;
import com.zhen.edu.service.EduSubjectService;
import com.zhen.servicebase.exception.SystemException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhen
 * @since 2023-02-09 09:50:58
 */
@Service("eduSubjectService")
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            //转换为文件输入流
            InputStream inputStream = file.getInputStream();

            //调用方法读取excel文件内容(并在监听器中添加到数据库)
            EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(eduSubjectService))
                    .sheet().doRead();

        }catch (Exception e){
            e.printStackTrace();
            throw new SystemException(ResultCode.ADD_ERROR.getCode(), ResultCode.ADD_ERROR.getMessage());
        }
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        QueryWrapper<EduSubject> eduSubjectQueryWrapper = new QueryWrapper<>();
        eduSubjectQueryWrapper.eq("parent_id",0);
        List<EduSubject> eduSubjects = baseMapper.selectList(eduSubjectQueryWrapper);


        QueryWrapper<EduSubject> twoQuery = new QueryWrapper<>();
        twoQuery.ne("parent_id","0");
        List<EduSubject> eduSubjectsTwo = baseMapper.selectList(twoQuery);

        List<OneSubject> oneSubjects = new ArrayList<>();

        for (int i = 0; i < eduSubjects.size(); i++) {
            //一级课程封装
            EduSubject eduSubject = eduSubjects.get(i);
            //一级课程类（bean copy 目标类）
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(eduSubject,oneSubject);

            ///////////////////////////////////////////////////////////////////
            //二级课程封装
            ArrayList<TwoSubject> twoSubjects = new ArrayList<>();

            for (int j = 0; j < eduSubjectsTwo.size(); j++) {

                EduSubject eduSubjectTwo = eduSubjectsTwo.get(j);
                //判断二级parent_id是否等于一级id
                if (eduSubjectTwo.getParentId().equals(eduSubject.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(eduSubjectTwo,twoSubject);
                    twoSubjects.add(twoSubject);
                }
            }

            oneSubject.setChildren(twoSubjects);

            //设置好全部内容，一级课程对象放到集合中
            oneSubjects.add(oneSubject);

        }
        return oneSubjects;
    }
}

