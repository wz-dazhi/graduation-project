package com.graduation.dto.req;

import com.graduation.dto.resp.BicyclePageResp;
import com.wz.common.util.JsonUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @projectName: graduation-project
 * @package: com.graduation.dto.req
 * @className: BicyclePageReq
 * @description:
 * @author: zhi
 * @date: 2022/6/19
 * @version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("单车分页请求")
public class BicyclePageReq extends BasePageReq<BicyclePageResp> {

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("分类")
    private String categoryName;

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
