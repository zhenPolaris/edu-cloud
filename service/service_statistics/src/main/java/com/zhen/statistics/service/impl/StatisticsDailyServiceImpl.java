package com.zhen.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhen.commonutils.R;
import com.zhen.statistics.client.UcenterClient;
import com.zhen.statistics.entity.StatisticsDaily;
import com.zhen.statistics.mapper.StatisticsDailyMapper;
import com.zhen.statistics.service.StatisticsDailyService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhen
 * @since 2023-02-20 08:49:08
 */
@Service("statisticsDailyService")
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    private UcenterClient ucenterClient;
    @Override
    public void createStatisticsByDay(String day) {
        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("date_calculated",day);
        baseMapper.delete(queryWrapper);

        //调用远程接口，查询某天注册人数
        R r = ucenterClient.countRegister(day);
        Integer countRegister = (Integer) r.getData().get("countRegister");


        StatisticsDaily statisticsDaily = new StatisticsDaily();
        statisticsDaily.setRegisterNum(countRegister);
        statisticsDaily.setCourseNum(RandomUtils.nextInt(100,200));
        statisticsDaily.setLoginNum(RandomUtils.nextInt(200,300));
        statisticsDaily.setVideoViewNum(RandomUtils.nextInt(200,300));
        statisticsDaily.setDateCalculated(day);

        baseMapper.insert(statisticsDaily);

    }

    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.select("date_calculated",type);
        wrapper.between("date_calculated", begin, end);

        List<StatisticsDaily> dailyList = baseMapper.selectList(wrapper);

        List<String> xlist = new ArrayList<>();
        List<Integer> ylist = new ArrayList<>();

        for (StatisticsDaily statisticsDaily : dailyList) {
            xlist.add(statisticsDaily.getDateCalculated());

            //判断查询的哪个字段
            if ("register_num".equals(type)){
                ylist.add(statisticsDaily.getRegisterNum());
            }
            if ("login_num".equals(type)){
                ylist.add(statisticsDaily.getLoginNum());
            }
            if ("video_view_num".equals(type)){
                ylist.add(statisticsDaily.getVideoViewNum());
            }
            if ("course_num".equals(type)){
                ylist.add(statisticsDaily.getCourseNum());
            }
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("xlist",xlist);
        map.put("ylist",ylist);

        return map;
    }
}

