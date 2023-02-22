package com.zhen.edu.controller;

import com.zhen.commonutils.R;
import com.zhen.edu.entity.EduVideo;
import com.zhen.edu.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhen
 * @since 2023-02-09 16:57:30
 */
@RestController
@CrossOrigin
@RequestMapping("eduservice/eduvideo")
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    //添加小节
    @PostMapping("/addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        boolean save = eduVideoService.save(eduVideo);
        if (save){
            return R.ok();
        }else{
            return R.error();
        }

    }


    //删除小节
    @DeleteMapping("/deleteVideo/{id}")
    public R deleteVideo(@PathVariable String id){
        eduVideoService.removeVideo(id);
        return R.ok();
    }

    //修改小节
    @PostMapping("/updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.updateById(eduVideo);
        return R.ok();
    }

    //根据小节id查询
    @GetMapping("/getVideoById/{videoId}")
    public R getVideoById(@PathVariable String videoId){
        EduVideo eduVideo = eduVideoService.getById(videoId);
        return R.ok().data("video",eduVideo);
    }


}



