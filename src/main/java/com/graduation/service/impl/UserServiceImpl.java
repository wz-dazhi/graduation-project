package com.graduation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.bean.Log;
import com.graduation.bean.User;
import com.graduation.constant.Const;
import com.graduation.dto.req.ChangePasswordReq;
import com.graduation.dto.req.IdsReq;
import com.graduation.dto.req.UserPageReq;
import com.graduation.enums.AppEnum;
import com.graduation.interceptor.UserHolder;
import com.graduation.mapper.UserMapper;
import com.graduation.service.LogService;
import com.graduation.service.UserService;
import com.graduation.util.LogHelper;
import com.wz.common.constant.DateConsts;
import com.wz.common.exception.BusinessException;
import com.wz.common.util.StringUtil;
import com.wz.datasource.mybatisplus.model.IPage;
import com.wz.webmvc.util.IpUtil;
import com.wz.webmvc.util.WebContextUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final LogService logService;

    @Override
    public IPage<User> page(UserPageReq req) {
        return baseMapper.page(req);
    }

    @Transactional
    @Override
    public boolean editor(User user) {
        return saveOrUpdate(user) && logService.save(LogHelper.log(user.msg()));
    }

    @Transactional
    @Override
    public void del(IdsReq req) {
        List<Long> ids = req.getIds();
        logService.save(LogHelper.log("删除用户. 用户ID: ", ids));
        removeByIds(ids);
    }

    @Transactional
    @Override
    public boolean changePassword(ChangePasswordReq req) {
        String p = req.getPassword();
        String np1 = req.getNewPassword();
        String np2 = req.getReNewPassword();

        if (p.equals(np1)) {
            throw new BusinessException("新密码不能与老密码相同!");
        } else if (!np1.equals(np2)) {
            throw new BusinessException("两次输入密码不一致!");
        }

        User currentUser = UserHolder.user();
        if (!currentUser.getPassword().equals(p)) {
            throw new BusinessException("当前密码不正确!");
        }
        User u = new User();
        u.setId(currentUser.getId());
        u.setPassword(np1);

        return updateById(u) && logService.save(LogHelper.log("修改密码, 用户ID: " + u.getId()));
    }

    @Transactional
    @Override
    public void login(User u) {
        if (null == u || StringUtil.isBlank(u.getUsername()) || StringUtil.isBlank(u.getPassword())) {
            throw new BusinessException("用户名密码不能为空");
        }

        String username = u.getUsername();
        String password = u.getPassword();
        User user = lambdaQuery().eq(User::getUsername, username).last(Const.LIMIT_1).one();
        if (user == null) {
            throw new BusinessException(AppEnum.USER_NOT_FOUND);
        } else if (!password.equals(user.getPassword())) {
            throw new BusinessException("密码错误");
        }
        user.setLoginTime(LocalDateTime.now());
        updateById(user);
        Log l = LogHelper.log(String.format("登录成功. 登录时间: %s, IP: %s", user.getLoginTime().format(DateConsts.DATE_TIME_HH_MM_SS_FORMATTER), IpUtil.getIp()));
        logService.save(l);
        WebContextUtil.getSession().setAttribute("id", user.getId());
    }

    @Override
    public void quit() {
        WebContextUtil.getSession().invalidate();
        Log l = LogHelper.log("退出登录. 退出时间: " + LocalDateTime.now().format(DateConsts.DATE_TIME_HH_MM_SS_FORMATTER));
        logService.save(l);
    }

}
