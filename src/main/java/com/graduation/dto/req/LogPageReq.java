package com.graduation.dto.req;

import com.graduation.bean.Log;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @projectName: graduation-project
 * @package: com.graduation.dto.req
 * @className: LogPageReq
 * @description:
 * @date: 2022/7/5
 * @version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("日志分页查询")
public class LogPageReq extends BasePageReq<Log> {
    @ApiModelProperty("操作人姓名")
    private String operatorName;

    @ApiModelProperty("操作信息")
    private String msg;

    @Override
    public String toString() {
        return super.toString();
    }
}
