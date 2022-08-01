package com.graduation.interceptor;

import com.graduation.bean.User;
import com.graduation.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @projectName: graduation-project
 * @package: com.graduation.interceptor
 * @className: UserInterceptor
 * @description:
 * @author: yue
 * @date: 2022/6/22
 * @version: 1.0
 */
@Slf4j
@Component
@AllArgsConstructor
public class UserInterceptor implements HandlerInterceptor {
    private final UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HttpSession session = req.getSession();
        Object uid = session.getAttribute("id");
        if (!(uid instanceof Long)) {
            resp.sendRedirect("/login.html");
            return false;
        }
        User u = userMapper.selectById((Long) uid);
        if (u == null) {
            resp.sendRedirect("/login.html");
            return false;
        }

        UserHolder.set(u);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception ex) throws Exception {
        UserHolder.remove();
    }


}
