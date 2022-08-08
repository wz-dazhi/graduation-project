package com.graduation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduation.bean.Bicycle;
import com.graduation.dto.req.BicyclePageReq;
import com.graduation.dto.req.IdsReq;
import com.graduation.dto.resp.BicyclePageResp;
import com.wz.datasource.common.mybatisplus.model.IPage;

public interface BicycleService extends IService<Bicycle> {

    IPage<BicyclePageResp> page(BicyclePageReq req);

    boolean editor(Bicycle bicycle);

    void del(IdsReq req);
}
