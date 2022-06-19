package com.graduation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graduation.bean.Bicycle;
import com.graduation.dto.req.BicyclePageReq;
import com.graduation.dto.req.IdsReq;
import com.graduation.dto.resp.BicyclePageResp;

public interface BicycleService extends IService<Bicycle> {

    IPage<BicyclePageResp> page(BicyclePageReq req);

    void del(IdsReq req);
}
