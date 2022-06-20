package com.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduation.bean.Order;
import com.graduation.dto.req.OrderPageReq;
import com.graduation.dto.resp.OrderPageResp;
import com.wz.datasource.mybatisplus.model.IPage;

public interface OrderMapper extends BaseMapper<Order> {
    IPage<OrderPageResp> page(OrderPageReq req);
}