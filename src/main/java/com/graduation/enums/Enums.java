package com.graduation.enums;

import com.wz.common.enums.IEnum;
import lombok.experimental.UtilityClass;

import java.util.Objects;
import java.util.Optional;

/**
 * @projectName: graduation-project
 * @package: com.graduation.enums
 * @className: Enums
 * @description:
 * @author: zhi
 * @date: 2022/6/20
 * @version: 1.0
 */
@UtilityClass
public class Enums {

    public static <C, D, E extends IEnum<C, D>> D desc(C code, E[] list) {
        return Optional.ofNullable(instance(code, list))
                .map(E::desc)
                .orElse(null);
    }

    public static <C, D, E extends IEnum<C, D>> E instance(C code, E[] list) {
        for (E e : list) {
            if (Objects.equals(code, e.code())) {
                return e;
            }
        }
        return null;
    }

}
