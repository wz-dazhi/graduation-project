package com.graduation.dto.resp;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

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
public class NamesValuesResp implements Serializable {
    private List<String> names;
    private List<BigDecimal> values;
}
