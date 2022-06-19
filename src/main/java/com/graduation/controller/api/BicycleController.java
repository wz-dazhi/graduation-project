package com.graduation.controller.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.graduation.bean.Bicycle;
import com.graduation.constant.Const;
import com.graduation.dto.req.BicyclePageReq;
import com.graduation.dto.req.IdsReq;
import com.graduation.dto.resp.BicyclePageResp;
import com.graduation.service.BicycleService;
import com.wz.swagger.model.Result;
import com.wz.swagger.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(Const.API + "/bicycle")
@AllArgsConstructor
public class BicycleController {
    private final BicycleService bicycleService;

    @PostMapping("/page")
    public Result<IPage<BicyclePageResp>> page(@RequestBody BicyclePageReq req) {
        return R.ok(bicycleService.page(req));
    }

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody Bicycle category) {
        return R.ok(bicycleService.save(category));
    }

    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody Bicycle category) {
        return R.ok(bicycleService.updateById(category));
    }

    @DeleteMapping("/del")
    public Result<Void> del(@Valid @RequestBody IdsReq req) {
        bicycleService.del(req);
        return R.ok();
    }

}
