package com.graduation.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.bean.Order;
import com.graduation.bean.Student;
import com.graduation.constant.Const;
import com.graduation.dto.req.IdsReq;
import com.graduation.dto.req.StudentPageReq;
import com.graduation.enums.SexEnum;
import com.graduation.mapper.OrderMapper;
import com.graduation.mapper.StudentMapper;
import com.graduation.service.StudentService;
import com.wz.common.exception.BusinessException;
import com.wz.datasource.mybatisplus.model.IPage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    private final OrderMapper orderMapper;

    @Override
    public IPage<Student> page(StudentPageReq req) {
        IPage<Student> p = baseMapper.page(req);
        p.consumer(s -> s.setSexDesc(SexEnum.desc(s.getSex())));
        return p;
    }

    @Override
    public boolean editor(Student s) {
        return s.getId() == null || s.getId() <= 0 ? save(s) : updateById(s);
    }

    @Override
    public boolean save(Student s) {
        s.setNo(IdWorker.getIdStr());
        return super.save(s);
    }

    @Override
    public void del(IdsReq req) {
        List<Long> ids = req.getIds();
        if (orderMapper.exists(Wrappers.<Order>lambdaQuery().in(Order::getSid, ids).last(Const.LIMIT_1))) {
            throw new BusinessException("删除的学生存在订单, 删除失败");
        }
        removeByIds(ids);
    }
}
