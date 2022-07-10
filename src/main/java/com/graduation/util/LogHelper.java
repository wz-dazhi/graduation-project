package com.graduation.util;

import com.graduation.bean.Log;
import com.graduation.interceptor.UserHolder;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @projectName: graduation-project
 * @package: com.graduation.util
 * @className: LogHelper
 * @description:
 * @author: zhi
 * @date: 2022/7/5
 * @version: 1.0
 */
@UtilityClass
public class LogHelper {

    public static Log log(String msg) {
        Long uid = UserHolder.user().getId();
        return Log.builder()
                .operatorId(uid)
                .msg(msg)
                .build();
    }

    public static Log log(String msg, Long uid) {
        return Log.builder()
                .operatorId(uid)
                .msg(msg)
                .build();
    }

    public static Log log(String msg, List<Long> ids) {
        msg += ids.stream().map(String::valueOf).collect(Collectors.joining(", "));
        return log(msg);
    }

}
