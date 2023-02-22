package com.zhen.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhen.order.entity.TOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhen
 * @since 2023-02-17 14:29:45
 */
/**
 * 订单(TOrder)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-17 14:29:45
 */
@Mapper
public interface TOrderMapper extends BaseMapper<TOrder> {

}

