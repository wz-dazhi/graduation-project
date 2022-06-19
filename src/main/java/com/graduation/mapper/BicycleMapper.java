package com.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.graduation.bean.Bicycle;
import com.graduation.dto.req.BicyclePageReq;
import com.graduation.dto.resp.BicyclePageResp;

public interface BicycleMapper extends BaseMapper<Bicycle> {
    IPage<BicyclePageResp> page(BicyclePageReq req);
}