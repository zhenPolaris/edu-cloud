package com.zhen.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhen.order.entity.TPayLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhen
 * @since 2023-02-17 14:30:25
 */
/**
 * 支付日志表(TPayLog)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-17 14:30:25
 */
@Mapper
public interface TPayLogMapper extends BaseMapper<TPayLog> {

}

