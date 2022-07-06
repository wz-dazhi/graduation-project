package com.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduation.bean.User;
import com.graduation.dto.req.UserPageReq;
import com.wz.datasource.mybatisplus.model.IPage;

public interface UserMapper extends BaseMapper<User> {
    IPage<User> page(UserPageReq req);
}