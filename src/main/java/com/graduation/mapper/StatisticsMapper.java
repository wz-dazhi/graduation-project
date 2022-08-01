package com.graduation.mapper;

import com.graduation.dto.resp.NameValueResp;

import java.math.BigDecimal;
import java.util.List;

/**
 * @projectName: graduation-project
 * @package: com.graduation.mapper
 * @className: StatisticsMapper
 * @description:
 * @author: yue
 * @date: 2022/7/11
 * @version: 1.0
 */
public interface StatisticsMapper {
    List<NameValueResp> income();

    BigDecimal currentMonthTotal();

    BigDecimal total();
}
