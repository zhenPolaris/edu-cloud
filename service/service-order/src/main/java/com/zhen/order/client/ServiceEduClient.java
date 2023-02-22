package com.zhen.order.client;

import com.zhen.commonutils.vo.EduCourseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author: zhen
 * @Create: 2023-02-17 15:10
 * @Description:
 */
@Component
@FeignClient("service-edu")
public interface ServiceEduClient {

    @PostMapping("/eduservice/educourse/getCourseInfoByIdOrder/{courseId}")
    public EduCourseVo getCourseInfoByIdOrder(@PathVariable String courseId);
}
