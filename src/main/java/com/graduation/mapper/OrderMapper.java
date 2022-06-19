package com.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.graduation.bean.Order;
import com.graduation.dto.req.OrderPageReq;
import com.graduation.dto.resp.OrderPageResp;

public interface OrderMapper extends BaseMapper<Order> {
    IPage<OrderPageResp> page(OrderPageReq req);
}