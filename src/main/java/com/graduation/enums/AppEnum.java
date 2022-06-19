package com.graduation.enums;

import com.wz.common.config.Resources;
import com.wz.common.exception.IErrorCode;
import lombok.AllArgsConstructor;

/**
 * @author zhi
 */
@AllArgsConstructor
public enum AppEnum implements IErrorCode {

    /**
     * 登录类型错误
     */
    LOGIN_TYPE_ERROR("0001"),

    /**
     * 用户不存在
     */
    USER_NOT_FOUND("0002"),
    ;

    private final String code;

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String desc() {
        return Resources.getMessage("i18n/app/app", this.code);
    }
}
