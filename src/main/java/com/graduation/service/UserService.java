package com.graduation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduation.bean.User;

public interface UserService extends IService<User> {

    void login(User u);

    void quit();
}
