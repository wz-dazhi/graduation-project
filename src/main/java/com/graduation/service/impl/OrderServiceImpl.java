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
            throw new BusinessException("单车[" + o.getBid() + "]不存在, 请重新输入");
        } else if (Objects.equals(b.getState(), BicycleEnum.BORROWED.code())) {
            throw new BusinessException("单车[" + o.getBid() + "]已出借, 请重新输入");
        } else if (Objects.equals(b.getState(), BicycleEnum.NEED_MAINTENANCE.code())) {
            throw new BusinessException("单车[" + o.getBid() + "]需要维修, 请重新输入");
        } else if (Objects.equals(b.getState(), BicycleEnum.SCRAPPED.code())) {
            throw new BusinessException("单车[" + o.getBid() + "]已报废, 请重新输入");
        }
        Category c = categoryMapper.selectById(b.getCid());
        if (c.getCash().compareTo(o.getCash()) != 0) {
            throw new BusinessException("押金收取错误, 应收[" + c.getCash() + "]元");
        }

        Long sid = o.getSid();
        if (!studentMapper.exists(Wrappers.<Student>lambdaQuery().eq(Student::getId, sid).last(Const.LIMIT_1))) {
            throw new BusinessException("学生[" + sid + "]不存在, 请重新输入");
        }
        boolean existsOrder = this.lambdaQuery()
                .eq(Order::getSid, sid)
                .eq(Order::getState, OrderEnum.NOT_RETURNED.code())
                .last(Const.LIMIT_1)
                .exists();
        if (existsOrder) {
            throw new BusinessException("学生[" + sid + "]存在未归还的订单");
        }

        o.setBorrowTime(LocalDateTime.now());
        o.setState(OrderEnum.NOT_RETURNED.code());
        b.setState(BicycleEnum.BORROWED.code());
        String msg = String.format("新增订单. 出借学生: %s,单车: %s,出借时间: %s", sid, o.getBid(), o.getBorrowTime().format(DateConsts.DATE_TIME_HH_MM_SS_FORMATTER));
        Log l = LogHelper.log(msg);
        return save(o) && bicycleMapper.updateById(b) == 1 && logService.save(l);
    }

    @Transactional
    @Override
    public boolean updateStatus(Order o) {
        Order currentOrder = getById(o.getId());
        if (currentOrder == null) {
            throw new BusinessException("订单不存在");
        } else if (!Objects.equals(currentOrder.getState(), OrderEnum.NOT_RETURNED.code())) {
            throw new BusinessException("订单已归还");
        }
        Integer state = o.getState();
        // 只更新备注, 不还车
        if (Objects.equals(state, OrderEnum.NOT_RETURNED.code())) {
            return updateById(o);
        }

        Bicycle b = bicycleMapper.selectById(currentOrder.getBid());
        Category c = categoryMapper.selectById(b.getCid());

        LocalDateTime returnTime = LocalDateTime.now();
        // 计算租金
        // 1. 需要维修扣除50%押金
        // 2. 报废扣除100%押金
        // 3. 小时*租金, 不到一小时按一小时计算, 不到1分钟不收费

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

        String msg = String.format("订单还车. 订单ID: %s, 学生: %s, 单车: %s, 归还时间: %s, 获得租金: %s, 归还状态: %s",
                o.getId(), o.getSid(), o.getBid(), returnTime, realRent, OrderEnum.desc(b.getState()));
        Log l = LogHelper.log(msg);

        return updateById(o) && bicycleMapper.updateById(b) == 1 && logService.save(l);
    }

    @Override
    public void del(IdsReq req) {
        throw new BusinessException("不允许删除订单");
//        List<Long> ids = req.getIds();
//        if (lambdaQuery().in(Order::getId, ids).eq(Order::getState, OrderEnum.NOT_RETURNED.code()).last(Const.LIMIT_1).exists()) {
//            throw new BusinessException("存在未归还的订单, 删除失败");
//        }
//        this.removeByIds(ids);
    }

}
