package com.graduation.dto;

import com.wz.common.model.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @projectName: graduation-project
 * @package: com.graduation.dto
 * @className: Base
 * @description:
 * @author: yue
 * @date: 2022/6/18
 * @version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Base extends BaseDto {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("备注")
    private String remark;

    @Override
    public String toString() {
        return super.toString();
    }
}
