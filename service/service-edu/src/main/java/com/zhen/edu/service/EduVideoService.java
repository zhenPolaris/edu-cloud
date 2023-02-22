package com.zhen.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhen.edu.entity.EduVideo;

/**
 * @author zhen
 * @since 2023-02-09 16:57:30
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeVideo(String id);


    public void removeVideoByCourseId(String id);
}

