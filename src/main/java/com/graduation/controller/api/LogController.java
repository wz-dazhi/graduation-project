package com.graduation.controller.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.graduation.bean.Log;
import com.graduation.constant.Const;
import com.graduation.dto.req.LogPageReq;
import com.graduation.service.LogService;
import com.wz.swagger.model.Result;
import com.wz.swagger.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Const.API + "/log")
@AllArgsConstructor
public class LogController {
    private final LogService logService;

    @PostMapping("/page")
    public Result<IPage<Log>> page(@RequestBody LogPageReq req) {
        return R.ok(logService.page(req));
    }
}
