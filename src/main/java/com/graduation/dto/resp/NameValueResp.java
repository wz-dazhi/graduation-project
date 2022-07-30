package com.graduation.dto.resp;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @projectName: graduation-project
 * @package: com.graduation.dto.resp
 * @className: NameValueResp
 * @description:
 * @author: yue
 * @date: 2022/7/11
 * @version: 1.0
 */
@Data
@Builder
public class NameValueResp implements Serializable {
    private String name;
    private BigDecimal value;
}
