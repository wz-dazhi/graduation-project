package com.graduation.enums;

import com.graduation.bean.Student;
import com.wz.common.enums.IEnum;
import com.wz.common.util.StringUtil;
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
public enum SexEnum implements IEnum<Short, String> {

    /**
     * 女
     */
    FEMALE((short) 1, "女"),
    /**
     * 男
     */
    MALE((short) 2, "男"),
    ;

    private final Short code;
    private final String desc;

    @Override
    public Short code() {
        return code;
    }

    @Override
    public String desc() {
        return desc;
    }

    public static String desc(Short code) {
        String desc = Enums.desc(code, values());
        return StringUtil.isBlank(desc) ? "未知" : desc;
    }

    public static void desc(Student s) {
        s.setSexDesc(desc(s.getSex()));
    }

}
