package com.graduation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.bean.User;
import com.graduation.constant.Const;
import com.graduation.mapper.UserMapper;
import com.graduation.service.UserService;
import com.wz.common.exception.BusinessException;
import com.wz.common.util.StringUtil;
import com.wz.webmvc.util.WebContextUtil;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

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
        WebContextUtil.getSession().setAttribute("id", user.getId());
    }

}
