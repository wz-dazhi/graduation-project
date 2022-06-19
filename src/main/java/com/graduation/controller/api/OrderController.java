package com.graduation.controller.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.graduation.bean.Order;
import com.graduation.constant.Const;
import com.graduation.dto.req.IdsReq;
import com.graduation.dto.req.OrderPageReq;
import com.graduation.dto.resp.OrderPageResp;
import com.graduation.service.OrderService;
import com.wz.swagger.model.Result;
import com.wz.swagger.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping(Const.API + "/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/page")
    public Result<IPage<OrderPageResp>> page(@RequestBody OrderPageReq req) {
        return R.ok(orderService.page(req));
    }

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody Order order) {
        order.setBorrowTime(LocalDateTime.now());
        return R.ok(orderService.save(order));
    }

    @PostMapping("/state_update")
    public Result<Boolean> update(@RequestBody Order order) {
        order.setReturnTime(LocalDateTime.now());
        return R.ok(orderService.updateById(order));
    }

    @DeleteMapping("/del")
    public Result<Void> del(@Valid @RequestBody IdsReq req) {
        orderService.del(req);
        return R.ok();
    }

}
