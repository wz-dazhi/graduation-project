package com.graduation.interceptor;

import com.graduation.bean.User;
import lombok.experimental.UtilityClass;

/**
 * @projectName: graduation-project
 * @package: com.graduation.interceptor
 * @className: UserHolder
 * @description:
 * @author: zhi
 * @date: 2022/6/22
 * @version: 1.0
 */
@UtilityClass
public class UserHolder {
    private static final ThreadLocal<User> USER_THREAD_LOCAL = new ThreadLocal<>();

    public static void set(User user) {
        USER_THREAD_LOCAL.set(user);
    }

    public static void remove() {
        USER_THREAD_LOCAL.remove();
    }

}
