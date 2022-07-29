package com.graduation.dto.req;

import com.graduation.dto.resp.OrderPageResp;
import com.wz.common.util.JsonUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @projectName: graduation-project
 * @package: com.graduation.dto.req
 * @className: OrderPageReq
 * @description:
 * @author: yue
 * @date: 2022/6/19
 * @version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("订单分页请求")
public class OrderPageReq extends BasePageReq<OrderPageResp> {

    @ApiModelProperty("订单ID")
    private Long id;

    @ApiModelProperty("学生ID")
    private Long sid;

    @ApiModelProperty("单车ID")
    private Long bid;

    @ApiModelProperty("学生姓名")
    private String studentName;

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
