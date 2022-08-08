package com.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduation.bean.Student;
import com.graduation.dto.req.StudentPageReq;
import com.wz.datasource.common.mybatisplus.model.IPage;

public interface StudentMapper extends BaseMapper<Student> {
    IPage<Student> page(StudentPageReq req);
}