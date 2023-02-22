package com.zhen.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhen.edu.entity.EduComment;
import com.zhen.edu.mapper.EduCommentMapper;
import com.zhen.edu.service.EduCommentService;
import org.springframework.stereotype.Service;

/**
 * @author zhen
 * @since 2023-02-17 13:00:21
 */
@Service("eduCommentService")
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {

}

