package com.graduation.dto.resp;

import com.graduation.dto.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @projectName: graduation-project
 * @package: com.graduation.dto.resp
 * @className: OrderPageResp
 * @description:
 * @author: zhi
 * @date: 2022/6/19
 * @version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("订单分页响应")
public class OrderPageResp extends Base {

    @ApiModelProperty("学生ID")
    private Long sid;

    @ApiModelProperty("单车ID")
    private Long bid;

    @ApiModelProperty("学生姓名")
    private String studentName;

    @ApiModelProperty("单车名称")
    private String bicycleName;

    @ApiModelProperty("出借时间")
    private LocalDateTime borrowTime;

    @ApiModelProperty("还车时间")
    private LocalDateTime returnTime;

    @ApiModelProperty("租金")
    private BigDecimal realRent;

    @ApiModelProperty("押金")
    private BigDecimal cash;

    @ApiModelProperty("状态")
    private Integer state;

    @Override
    public String toString() {
        return super.toString();
    }

}
