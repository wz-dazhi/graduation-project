package com.graduation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.bean.Log;
import com.graduation.bean.User;
import com.graduation.constant.Const;
import com.graduation.mapper.UserMapper;
import com.graduation.service.LogService;
import com.graduation.service.UserService;
import com.graduation.util.LogHelper;
import com.wz.common.exception.BusinessException;
import com.wz.common.util.StringUtil;
import com.wz.webmvc.util.IpUtil;
import com.wz.webmvc.util.WebContextUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final LogService logService;

    @Override
    public void login(User u) {
        if (null == u || StringUtil.isBlank(u.getUsername()) || StringUtil.isBlank(u.getPassword())) {
            throw new BusinessException("用户名密码不能为空");
        }

        String username = u.getUsername();
        String password = u.getPassword();
        User user = lambdaQuery().eq(User::getUsername, username).last(Const.LIMIT_1).one();
        if (user == null) {
            throw new BusinessException("用户不存在");
        } else if (!password.equals(user.getPassword())) {
            throw new BusinessException("密码错误");
        }
        Log l = LogHelper.log("登录成功, IP: " + IpUtil.getIp());
        logService.save(l);
        WebContextUtil.getSession().setAttribute("id", user.getId());
    }

    @Override
    public void quit() {
        WebContextUtil.getSession().invalidate();
        Log l = LogHelper.log("退出登录.");
        logService.save(l);
    }

}
