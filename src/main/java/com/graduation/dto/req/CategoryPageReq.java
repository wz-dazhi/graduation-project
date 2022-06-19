package com.graduation.dto.req;

import com.graduation.bean.Category;
import com.wz.common.util.JsonUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @projectName: graduation-project
 * @package: com.graduation.dto
 * @className: CategoryPageReq
 * @description:
 * @author: zhi
 * @date: 2022/6/18
 * @version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("分类分页请求")
public class CategoryPageReq extends BasePageReq<Category> {

    @ApiModelProperty("品牌名称")
    private String name;

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
