package com.zhen.statistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhen.statistics.entity.StatisticsDaily;

import java.util.Map;

/**
 * @author zhen
 * @since 2023-02-20 08:49:08
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void createStatisticsByDay(String day);

    Map<String, Object> getShowData(String type, String begin, String end);
}

