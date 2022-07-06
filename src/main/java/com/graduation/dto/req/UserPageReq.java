package com.graduation.dto.req;

import com.graduation.bean.User;
import com.wz.common.util.JsonUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @projectName: graduation-project
 * @package: com.graduation.dto.req
 * @className: StudentPageReq
 * @description:
 * @date: 2022/6/20
 * @version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("用户分页请求")
public class UserPageReq extends BasePageReq<User> {

    @ApiModelProperty("姓名")
    private String name;

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
