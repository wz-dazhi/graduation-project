package com.graduation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduation.bean.Order;
import com.graduation.dto.req.IdsReq;
import com.graduation.dto.req.OrderPageReq;
import com.graduation.dto.resp.OrderPageResp;
import com.wz.datasource.mybatisplus.model.IPage;

public interface OrderService extends IService<Order> {

    IPage<OrderPageResp> page(OrderPageReq req);

    boolean add(Order o);

    boolean updateStatus(Order o);

    void del(IdsReq req);
}
