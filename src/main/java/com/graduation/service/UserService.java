package com.graduation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduation.bean.User;
import com.graduation.dto.req.ChangePasswordReq;
import com.graduation.dto.req.IdsReq;
import com.graduation.dto.req.UserPageReq;
import com.wz.datasource.mybatisplus.model.IPage;

public interface UserService extends IService<User> {

    IPage<User> page(UserPageReq req);

    User login(User u);

    void quit();

    boolean editor(User user);

    void del(IdsReq req);

    boolean changePassword(ChangePasswordReq req);
}
