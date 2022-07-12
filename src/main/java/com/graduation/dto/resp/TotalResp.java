package com.graduation.dto.resp;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @projectName: graduation-project
 * @package: com.graduation.dto.resp
 * @className: TotalResp
 * @description:
 * @author: zhi
 * @date: 2022/7/11
 * @version: 1.0
 */
@Data
@Builder
public class TotalResp implements Serializable {
    private BigDecimal currentMonthTotal;
    private BigDecimal total;
}
