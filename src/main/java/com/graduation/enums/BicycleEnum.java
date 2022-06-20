package com.graduation.enums;

import com.wz.common.enums.IEnum;
import lombok.AllArgsConstructor;

/**
 * @projectName: graduation-project
 * @package: com.graduation.enums
 * @className: BicycleEnum
 * @description:
 * @author: zhi
 * @date: 2022/6/20
 * @version: 1.0
 */
@AllArgsConstructor
public enum BicycleEnum implements IEnum<Integer, String> {
    /**
     * 未借
     */
    NOT_BORROWED(1, "未借"),
    /**
     * 已借
     */
    BORROWED(2, "已借"),
    /**
     * 需维修
     */
    NEED_MAINTENANCE(3, "需维修"),
    /**
     * 报废
     */
    SCRAPPED(4, "报废"),
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
