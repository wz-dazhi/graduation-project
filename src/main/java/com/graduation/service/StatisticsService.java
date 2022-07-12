package com.graduation.service;

import com.graduation.dto.resp.NameValueResp;
import com.graduation.dto.resp.NamesValuesResp;
import com.graduation.dto.resp.TotalResp;

import java.util.List;

/**
 * @projectName: graduation-project
 * @package: com.graduation.service
 * @className: StatisticsService
 * @description:
 * @author: zhi
 * @date: 2022/7/11
 * @version: 1.0
 */
public interface StatisticsService {
    List<NameValueResp> bicycle();
    List<NameValueResp> student();
    NamesValuesResp income();
    TotalResp total();
}
