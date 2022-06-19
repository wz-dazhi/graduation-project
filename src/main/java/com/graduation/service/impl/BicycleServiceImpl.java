package com.graduation.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.bean.Bicycle;
import com.graduation.dto.req.BicyclePageReq;
import com.graduation.dto.req.IdsReq;
import com.graduation.dto.resp.BicyclePageResp;
import com.graduation.mapper.BicycleMapper;
import com.graduation.service.BicycleService;
import org.springframework.stereotype.Service;

@Service
public class BicycleServiceImpl extends ServiceImpl<BicycleMapper, Bicycle> implements BicycleService {

    @Override
    public IPage<BicyclePageResp> page(BicyclePageReq req) {
        return baseMapper.page(req);
    }

    @Override
    public void del(IdsReq req) {
        this.removeByIds(req.getIds());
    }
}
