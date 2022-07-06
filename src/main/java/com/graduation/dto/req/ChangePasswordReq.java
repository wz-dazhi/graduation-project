package com.graduation.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @projectName: graduation-project
 * @package: com.graduation.dto.req
 * @className: ChangePasswordReq
 * @description:
 * @author: zhi
 * @date: 2022/7/5
 * @version: 1.0
 */
@Data
@ApiModel("修改密码请求")
public class ChangePasswordReq implements Serializable {
    @NotNull(message = "{change.password.NotNull}")
    @NotBlank(message = "{change.password.NotBlank}")
    @ApiModelProperty("当前密码")
    private String password;

    @NotNull(message = "{change.newPassword.NotNull}")
    @NotBlank(message = "{change.newPassword.NotBlank}")
    @ApiModelProperty("新密码")
    private String newPassword;

    @NotNull(message = "{change.reNewPassword.NotNull}")
    @NotBlank(message = "{change.reNewPassword.NotBlank}")
    @ApiModelProperty("重复新密码")
    private String reNewPassword;
}
