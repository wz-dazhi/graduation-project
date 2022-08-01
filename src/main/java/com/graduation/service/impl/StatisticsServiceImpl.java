package com.graduation.service.impl;

import com.graduation.bean.Bicycle;
import com.graduation.dto.resp.NameValueResp;
import com.graduation.dto.resp.NamesValuesResp;
import com.graduation.dto.resp.TotalResp;
import com.graduation.enums.BicycleEnum;
import com.graduation.mapper.StatisticsMapper;
import com.graduation.service.BicycleService;
import com.graduation.service.OrderService;
import com.graduation.service.StatisticsService;
import com.graduation.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @projectName: graduation-project
 * @package: com.graduation.service.impl
 * @className: StatisticsServiceImpl
 * @description:
 * @author: yue
 * @date: 2022/7/11
 * @version: 1.0
 */
@Service
@AllArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    private final StatisticsMapper statisticsMapper;
    private final StudentService studentService;
    private final OrderService orderService;
    private final BicycleService bicycleService;

    @Override
    public List<NameValueResp> bicycle() {
        NameValueResp total = nameValue("总计", bicycleService.count());
        NameValueResp normal = nameValue("正常", bicycleService.lambdaQuery().in(Bicycle::getState, BicycleEnum.NOT_BORROWED.code(), BicycleEnum.BORROWED.code()).count());
        NameValueResp maintenance = nameValue("维修", bicycleService.lambdaQuery().eq(Bicycle::getState, BicycleEnum.NEED_MAINTENANCE.code()).count());
        NameValueResp scrapped = nameValue("报废", bicycleService.lambdaQuery().eq(Bicycle::getState, BicycleEnum.SCRAPPED.code()).count());
        return List.of(total, normal, maintenance, scrapped);
    }

    @Override
    public List<NameValueResp> student() {
        NameValueResp studentCount = nameValue("学生人数", studentService.count());
        NameValueResp orderCount = nameValue("借车人次", orderService.count());
        return List.of(studentCount, orderCount);
    }

    private NameValueResp nameValue(String name, Long value) {
        return NameValueResp.builder().name(name).value(BigDecimal.valueOf(value)).build();
    }

    @Override
    public NamesValuesResp income() {
        List<NameValueResp> list = statisticsMapper.income();
        NamesValuesResp resp = new NamesValuesResp();
        resp.setNames(list.stream().map(NameValueResp::getName).collect(Collectors.toList()));
        resp.setValues(list.stream().map(NameValueResp::getValue).collect(Collectors.toList()));
        return resp;
    }

    @Override
    public TotalResp total() {
        return TotalResp.builder()
                .currentMonthTotal(statisticsMapper.currentMonthTotal())
                .total(statisticsMapper.total())
                .build();
    }
}
