package com.graduation.controller.api;

import com.graduation.constant.Const;
import com.graduation.dto.resp.NameValueResp;
import com.graduation.dto.resp.NamesValuesResp;
import com.graduation.dto.resp.TotalResp;
import com.graduation.service.StatisticsService;
import com.wz.swagger.model.Result;
import com.wz.swagger.util.R;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @projectName: graduation-project
 * @package: com.graduation.controller.api
 * @className: StatisticsController
 * @description:
 * @author: zhi
 * @date: 2022/7/11
 * @version: 1.0
 */
@Api(tags = "统计服务")
@RestController
@RequestMapping(Const.API + "/statistics")
@AllArgsConstructor
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/bicycle")
    public Result<List<NameValueResp>> bicycle() {
        return R.ok(statisticsService.bicycle());
    }

    @GetMapping("/student")
    public Result<List<NameValueResp>> student() {
        return R.ok(statisticsService.student());
    }

    @GetMapping("/income")
    public Result<NamesValuesResp> income() {
        return R.ok(statisticsService.income());
    }

    @GetMapping("/total")
    public Result<TotalResp> total() {
        return R.ok(statisticsService.total());
    }
}
