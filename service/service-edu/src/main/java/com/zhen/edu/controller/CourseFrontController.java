package com.zhen.edu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhen.commonutils.JwtUtils;
import com.zhen.commonutils.R;
import com.zhen.edu.client.OrderClient;
import com.zhen.edu.entity.EduCourse;
import com.zhen.edu.entity.vo.ChapterVo;
import com.zhen.edu.entity.vo.CourseFrontVo;
import com.zhen.edu.entity.vo.CourseWebVo;
import com.zhen.edu.service.EduChapterService;
import com.zhen.edu.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/******
 @author 阿昌
 @create 2021-03-07 13:21
 *******
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/courseFront")
public class CourseFrontController {

    @Autowired
    private EduCourseService eduCourseService;
    
    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private OrderClient orderClient;


    //前台多条件分页查询
    @PostMapping("/getConditionPage/{page}/{limit}")
    public R getConditionPage(@PathVariable Long page,
                              @PathVariable Long limit,
                              @RequestBody(required = false) CourseFrontVo courseFrontVo){
        Page<EduCourse> pageCourse = new Page<>(page, limit);
        Map<String,Object> map = eduCourseService.getCourseFrontInfo(pageCourse,courseFrontVo);

        return R.ok().data(map);
    }

    //课程详情的方法
    @GetMapping("/getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request){
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = eduCourseService.getBaseCourseInfo(courseId);
        //根据课程id，查询章节和小节信息
        List<ChapterVo> chapterVideoList = eduChapterService.getChapterVideoByCourseId(courseId);

        String memberId = JwtUtils.getMemberIdByJwtToken(request);

        System.out.println(memberId);

        Boolean isBuy = false;
        if (!StringUtils.isEmpty(memberId)){
            isBuy = orderClient.isBuyCourse(memberId, courseId);
            System.out.println(isBuy);
        }


        return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList).data("isBuy",isBuy);

    }


}
