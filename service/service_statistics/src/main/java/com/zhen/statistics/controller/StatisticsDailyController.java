package com.zhen.statistics.controller;

import com.zhen.commonutils.R;
import com.zhen.statistics.service.StatisticsDailyService;
import org.apache.http.conn.util.PublicSuffixList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author zhen
 * @since 2023-02-20 08:49:08
 */
@RestController
@CrossOrigin
@RequestMapping("/staservice/daily")
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService statisticsDailyService;


    @PostMapping("/createStatisticsByDay/{day}")
    public R createStatisticsByDay(@PathVariable String day){
        statisticsDailyService.createStatisticsByDay(day);
        return R.ok();
    }

    @GetMapping("/showData/{type}/{begin}/{end}")
    public R showData(@PathVariable String type,@PathVariable String begin,@PathVariable String end){
        Map<String,Object> map = statisticsDailyService.getShowData(type,begin,end);
        return R.ok().data(map);
    }


}



