package com.graduation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduation.bean.Log;
import com.graduation.dto.req.LogPageReq;
import com.wz.datasource.common.mybatisplus.model.IPage;

public interface LogService extends IService<Log> {

    IPage<Log> page(LogPageReq req);
}
