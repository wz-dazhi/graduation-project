package com.graduation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduation.bean.Student;
import com.graduation.dto.req.IdsReq;
import com.graduation.dto.req.StudentPageReq;
import com.wz.datasource.mybatisplus.model.IPage;

public interface StudentService extends IService<Student> {

    IPage<Student> page(StudentPageReq req);

    boolean editor(Student s);

    void del(IdsReq req);
}
