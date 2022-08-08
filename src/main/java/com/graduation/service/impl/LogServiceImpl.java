package com.graduation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.bean.Log;
import com.graduation.dto.req.LogPageReq;
import com.graduation.mapper.LogMapper;
import com.graduation.service.LogService;
import com.wz.datasource.common.mybatisplus.model.IPage;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

    @Override
    public IPage<Log> page(LogPageReq req) {
        return baseMapper.page(req);
    }

}
