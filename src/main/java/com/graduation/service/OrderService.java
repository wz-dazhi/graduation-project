package com.graduation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graduation.bean.Order;
import com.graduation.dto.req.IdsReq;
import com.graduation.dto.req.OrderPageReq;
import com.graduation.dto.resp.OrderPageResp;

public interface OrderService extends IService<Order> {

    IPage<OrderPageResp> page(OrderPageReq req);

    void del(IdsReq req);
}
