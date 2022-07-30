package com.graduation.enums;

import com.wz.common.enums.IEnum;
import lombok.AllArgsConstructor;

/**
 * @projectName: graduation-project
 * @package: com.graduation.enums
 * @className: OrderEnum
 * @description:
 * @author: yue
 * @date: 2022/6/20
 * @version: 1.0
 */
@AllArgsConstructor
public enum OrderEnum implements IEnum<Integer, String> {
    /**
     * 未归还
     */
    NOT_RETURNED(1, "未归还"),
    /**
     * 归还
     */
    RETURNED(2, "归还"),
    /**
     * 归还(需维修)
     */
    RETURNED_REQUIRES_REPAIR(3, "归还(需维修)"),
    /**
     * 归还(报废)
     */
    RETURNED_SCRAP(4, "归还(报废)"),
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
