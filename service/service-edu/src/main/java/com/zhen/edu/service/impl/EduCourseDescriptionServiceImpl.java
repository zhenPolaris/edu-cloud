package com.zhen.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhen.edu.entity.EduCourseDescription;
import com.zhen.edu.mapper.EduCourseDescriptionMapper;
import com.zhen.edu.service.EduCourseDescriptionService;
import org.springframework.stereotype.Service;

/**
 * @author zhen
 * @since 2023-02-09 16:54:41
 */
@Service("eduCourseDescriptionService")
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription> implements EduCourseDescriptionService {

}

