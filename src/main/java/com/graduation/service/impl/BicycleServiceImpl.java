package com.graduation.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.bean.Bicycle;
import com.graduation.bean.Log;
import com.graduation.bean.Order;
import com.graduation.constant.Const;
import com.graduation.dto.req.BicyclePageReq;
import com.graduation.dto.req.IdsReq;
import com.graduation.dto.resp.BicyclePageResp;
import com.graduation.enums.BicycleEnum;
import com.graduation.mapper.BicycleMapper;
import com.graduation.mapper.CategoryMapper;
import com.graduation.mapper.LogMapper;
import com.graduation.mapper.OrderMapper;
import com.graduation.service.BicycleService;
import com.graduation.util.LogHelper;
import com.wz.common.exception.BusinessException;
import com.wz.datasource.mybatisplus.model.IPage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BicycleServiceImpl extends ServiceImpl<BicycleMapper, Bicycle> implements BicycleService {
    private final CategoryMapper categoryMapper;
    private final OrderMapper orderMapper;
    private final LogMapper logMapper;

    @Override
    public IPage<BicyclePageResp> page(BicyclePageReq req) {
        IPage<BicyclePageResp> p = baseMapper.page(req);
        p.consumer(b -> b.setStateDesc(BicycleEnum.desc(b.getState())));
        return p;
    }

    @Transactional
    @Override
    public boolean editor(Bicycle bicycle) {
        if (categoryMapper.selectById(bicycle.getCid()) == null) {
            throw new BusinessException("没有找到[" + bicycle.getCid() + "]的分类");
        }
        Log l = LogHelper.log(bicycle.msg());
        logMapper.insert(l);
        return bicycle.getId() == null ? save(bicycle) : updateById(bicycle);
    }

    @Transactional
    @Override
    public void del(IdsReq req) {
        List<Long> ids = req.getIds();
        for (Long id : ids) {
            if (orderMapper.exists(Wrappers.<Order>lambdaQuery().eq(Order::getBid, id).last(Const.LIMIT_1))) {
                throw new BusinessException("单车[" + id + "]存在订单, 不能删除");
            }
        }

        Log l = LogHelper.log("删除单车. 单车ID: ", ids);
        logMapper.insert(l);
        this.removeByIds(ids);
    }
}
