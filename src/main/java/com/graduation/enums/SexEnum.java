package com.graduation.enums;

import com.wz.common.enums.IEnum;
import lombok.AllArgsConstructor;

/**
 * @projectName: graduation-project
 * @package: com.graduation.enums
 * @className: SexEnum
 * @description:
 * @author: zhi
 * @date: 2022/6/20
 * @version: 1.0
 */
@AllArgsConstructor
public enum SexEnum implements IEnum<Integer, String> {

    /**
     * 女
     */
    FEMALE(1, "女"),
    /**
     * 男
     */
    MALE(2, "男"),
    ;

    private final Integer code;
    private final String desc;

    @Override
    public Integer code() {
        return code;
    }

    @Override
    public String desc() {
        return desc;
    }

    public static String desc(Integer code) {
        return Enums.desc(code, values());
    }

}
