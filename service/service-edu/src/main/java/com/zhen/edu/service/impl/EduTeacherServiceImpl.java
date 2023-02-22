package com.zhen.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhen.edu.entity.EduTeacher;
import com.zhen.edu.entity.dto.TeacherQueryDTO;
import com.zhen.edu.mapper.EduTeacherMapper;
import com.zhen.edu.service.EduTeacherService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 讲师(EduTeacher)表服务实现类
 *
 * @author makejava
 * @since 2023-02-04 15:20:09
 */
@Service("eduTeacherService")
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void pageQuery(Page<EduTeacher> pageParam, TeacherQueryDTO teacherQueryDTO) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");

        if (teacherQueryDTO == null){
            baseMapper.selectPage(pageParam,queryWrapper);
            return;
        }
        String name = teacherQueryDTO.getName();
        Integer level = teacherQueryDTO.getLevel();
        String begin = teacherQueryDTO.getBegin();
        String end = teacherQueryDTO.getEnd();

        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }

        if (!StringUtils.isEmpty(level)){
            queryWrapper.eq("level",level);
        }

        if (!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }

        if (!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_create",end);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public Map<String, Object> getTeacherFrontPageList(Page<EduTeacher> teacherPage) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        IPage<EduTeacher> eduTeacherIPage = baseMapper.selectPage(teacherPage, queryWrapper);

        HashMap<String, Object> map = new HashMap<>();

        //总记录数
        long total = teacherPage.getTotal();
        //当前页
        long current = teacherPage.getCurrent();
        //每页记录数
        long size = teacherPage.getSize();
        //查询到的对象
        List<EduTeacher> teacherList = teacherPage.getRecords();
        //总页数
        long pages = teacherPage.getPages();
        //是否有上一页
        boolean hasPrevious = teacherPage.hasPrevious();
        //是否有下一页
        boolean hasNext = teacherPage.hasNext();

        //将数据封装到map中返回
        map.put("total",total);
        map.put("current",current);
        map.put("size",size);
        map.put("list",teacherList);
        map.put("hasPrevious",hasPrevious);
        map.put("hasNext",hasNext);
        map.put("pages",pages);

        return map;
    }
}

