package com.zhen.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhen.commonutils.R;
import com.zhen.edu.client.VodClient;
import com.zhen.edu.entity.EduVideo;
import com.zhen.edu.mapper.EduVideoMapper;
import com.zhen.edu.service.EduVideoService;
import com.zhen.servicebase.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhen
 * @since 2023-02-09 16:57:30
 */
@Service("eduVideoService")
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    //根据课程id删除小节
    @Override
    public void removeVideo(String id) {
        //查询云端视频id
        EduVideo eduVideo = baseMapper.selectById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        //判断小节中是否有对应的视频文件
        if (!StringUtils.isEmpty(videoSourceId)){
            //有就删除
            R r = vodClient.removeAliyunVideoById(videoSourceId);
            if (r.getCode() == 20001){
                throw new SystemException(20001,"熔断");
            }
        }

        //删除小节
        baseMapper.deleteById(id);

    }

    //根据课程id删除小节
    @Override
    public void removeVideoByCourseId(String id) {

        //根据课程id查询课程里面的所有视频
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        wrapper.select("video_source_id");
        List<EduVideo> eduVideos = baseMapper.selectList(wrapper);

        List<String> list = new ArrayList<>();
        for (EduVideo eduVideo : eduVideos) {
            String sourceId = eduVideo.getVideoSourceId();
            if (!StringUtils.isEmpty(sourceId)){
                list.add(sourceId);
            }

        }

        //根据多个视频id，删除多个视频
        if (list.size()>0){
            vodClient.removeBatch(list);
        }

        QueryWrapper<EduVideo> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("course_id", id);
        baseMapper.delete(queryWrapper2);

    }


}

