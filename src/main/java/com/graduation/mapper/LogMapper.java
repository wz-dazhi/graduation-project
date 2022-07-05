package com.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduation.bean.Log;
import com.graduation.dto.req.LogPageReq;
import com.wz.datasource.mybatisplus.model.IPage;

public interface LogMapper extends BaseMapper<Log> {
    IPage<Log> page(LogPageReq req);
}