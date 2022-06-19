package com.graduation.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.bean.Order;
import com.graduation.dto.req.IdsReq;
import com.graduation.dto.req.OrderPageReq;
import com.graduation.dto.resp.OrderPageResp;
import com.graduation.mapper.OrderMapper;
import com.graduation.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public IPage<OrderPageResp> page(OrderPageReq req) {
        return baseMapper.page(req);
    }

    @Override
    public void del(IdsReq req) {
        this.removeByIds(req.getIds());
    }
}
