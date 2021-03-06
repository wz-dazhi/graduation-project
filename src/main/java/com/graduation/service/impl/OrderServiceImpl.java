package com.graduation.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.bean.Bicycle;
import com.graduation.bean.Category;
import com.graduation.bean.Log;
import com.graduation.bean.Order;
import com.graduation.bean.Student;
import com.graduation.constant.Const;
import com.graduation.dto.req.IdsReq;
import com.graduation.dto.req.OrderPageReq;
import com.graduation.dto.resp.OrderPageResp;
import com.graduation.enums.BicycleEnum;
import com.graduation.enums.OrderEnum;
import com.graduation.mapper.BicycleMapper;
import com.graduation.mapper.CategoryMapper;
import com.graduation.mapper.OrderMapper;
import com.graduation.mapper.StudentMapper;
import com.graduation.service.LogService;
import com.graduation.service.OrderService;
import com.graduation.util.LogHelper;
import com.wz.common.constant.DateConsts;
import com.wz.common.exception.BusinessException;
import com.wz.common.util.DateUtil;
import com.wz.datasource.mybatisplus.model.IPage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    private final BicycleMapper bicycleMapper;
    private final CategoryMapper categoryMapper;
    private final StudentMapper studentMapper;
    private final LogService logService;

    @Override
    public IPage<OrderPageResp> page(OrderPageReq req) {
        IPage<OrderPageResp> p = baseMapper.page(req);
        p.consumer(r -> r.setStateDesc(OrderEnum.desc(r.getState())));
        return p;
    }

    @Transactional
    @Override
    public boolean add(Order o) {
        Bicycle b = bicycleMapper.selectById(o.getBid());
        if (b == null) {
            throw new BusinessException("??????[" + o.getBid() + "]?????????, ???????????????");
        } else if (Objects.equals(b.getState(), BicycleEnum.BORROWED.code())) {
            throw new BusinessException("??????[" + o.getBid() + "]?????????, ???????????????");
        } else if (Objects.equals(b.getState(), BicycleEnum.NEED_MAINTENANCE.code())) {
            throw new BusinessException("??????[" + o.getBid() + "]????????????, ???????????????");
        } else if (Objects.equals(b.getState(), BicycleEnum.SCRAPPED.code())) {
            throw new BusinessException("??????[" + o.getBid() + "]?????????, ???????????????");
        }
        Category c = categoryMapper.selectById(b.getCid());
        if (c.getCash().compareTo(o.getCash()) != 0) {
            throw new BusinessException("??????????????????, ??????[" + c.getCash() + "]???");
        }

        Long sid = o.getSid();
        if (!studentMapper.exists(Wrappers.<Student>lambdaQuery().eq(Student::getId, sid).last(Const.LIMIT_1))) {
            throw new BusinessException("??????[" + sid + "]?????????, ???????????????");
        }
        boolean existsOrder = this.lambdaQuery()
                .eq(Order::getSid, sid)
                .eq(Order::getState, OrderEnum.NOT_RETURNED.code())
                .last(Const.LIMIT_1)
                .exists();
        if (existsOrder) {
            throw new BusinessException("??????[" + sid + "]????????????????????????");
        }

        o.setBorrowTime(LocalDateTime.now());
        o.setState(OrderEnum.NOT_RETURNED.code());
        b.setState(BicycleEnum.BORROWED.code());
        String msg = String.format("????????????. ????????????: %s,??????: %s,????????????: %s", sid, o.getBid(), o.getBorrowTime().format(DateConsts.DATE_TIME_HH_MM_SS_FORMATTER));
        Log l = LogHelper.log(msg);
        return logService.save(l) && save(o) && bicycleMapper.updateById(b) == 1;
    }

    @Transactional
    @Override
    public boolean updateStatus(Order o) {
        Order currentOrder = getById(o.getId());
        if (currentOrder == null) {
            throw new BusinessException("???????????????");
        } else if (!Objects.equals(currentOrder.getState(), OrderEnum.NOT_RETURNED.code())) {
            throw new BusinessException("???????????????");
        }
        Integer state = o.getState();
        // ???????????????, ?????????
        if (Objects.equals(state, OrderEnum.NOT_RETURNED.code())) {
            return updateById(o);
        }

        Bicycle b = bicycleMapper.selectById(currentOrder.getBid());
        Category c = categoryMapper.selectById(b.getCid());

        LocalDateTime returnTime = LocalDateTime.now();
        // ????????????
        // 1. ??????????????????50%??????
        // 2. ????????????100%??????
        // 3. ??????*??????, ?????????????????????????????????, ??????1???????????????

        BigDecimal realRent;
        if (Objects.equals(state, OrderEnum.RETURNED_REQUIRES_REPAIR.code())) {
            realRent = currentOrder.getCash().divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_UP);
            b.setState(BicycleEnum.NEED_MAINTENANCE.code());
        } else if (Objects.equals(state, OrderEnum.RETURNED_SCRAP.code())) {
            realRent = currentOrder.getCash();
            b.setState(BicycleEnum.SCRAPPED.code());
        } else {
            LocalDateTime borrowTime = currentOrder.getBorrowTime();
            long minutes = DateUtil.betweenMinutes(borrowTime, returnTime);
            long hours = minutes / 60;
            hours = minutes % 60 == 0 ? hours : ++hours;
            realRent = c.getRealRent().multiply(BigDecimal.valueOf(hours)).setScale(2, RoundingMode.HALF_UP);
            b.setState(BicycleEnum.NOT_BORROWED.code());
        }
        o.setReturnTime(returnTime);
        o.setRealRent(realRent);

        String msg = String.format("????????????. ??????ID: %s, ??????: %s, ??????: %s, ????????????: %s, ????????????: %s, ????????????: %s",
                o.getId(), currentOrder.getSid(), currentOrder.getBid(), returnTime.format(DateConsts.DATE_TIME_HH_MM_SS_FORMATTER), realRent, OrderEnum.desc(b.getState()));
        Log l = LogHelper.log(msg);

        return logService.save(l) && updateById(o) && bicycleMapper.updateById(b) == 1;
    }

    @Override
    public void del(IdsReq req) {
        throw new BusinessException("?????????????????????");
//        List<Long> ids = req.getIds();
//        if (lambdaQuery().in(Order::getId, ids).eq(Order::getState, OrderEnum.NOT_RETURNED.code()).last(Const.LIMIT_1).exists()) {
//            throw new BusinessException("????????????????????????, ????????????");
//        }
//        this.removeByIds(ids);
    }

}
