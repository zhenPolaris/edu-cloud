package com.zhen.statistics.schedule;

import com.zhen.statistics.service.StatisticsDailyService;
import com.zhen.statistics.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: zhen
 * @Create: 2023-02-20 11:25
 * @Description:
 */
@Component
public class ScheduledTask {
    @Autowired
    private StatisticsDailyService statisticsDailyService;


    //在每天凌晨1点执行方法，把前一天的数据查询进行添加
    @Scheduled(cron = "0 0 1 * * ? ")//指定cron表达式规则
    public void task02(){
        statisticsDailyService.createStatisticsByDay(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }


}
