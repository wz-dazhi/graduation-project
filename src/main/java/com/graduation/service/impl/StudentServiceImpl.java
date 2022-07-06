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
import com.graduation.service.LogService;
import com.graduation.service.StudentService;
import com.graduation.util.LogHelper;
import com.wz.common.exception.BusinessException;
import com.wz.datasource.mybatisplus.model.IPage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    private final OrderMapper orderMapper;
    private final LogService logService;

    @Override
    public IPage<Student> page(StudentPageReq req) {
        IPage<Student> p = baseMapper.page(req);
        p.consumer(SexEnum::desc);
        return p;
    }

    @Transactional
    @Override
    public boolean editor(Student s) {
        return s.getId() == null || s.getId() <= 0 ? save(s) : updateById(s) && logService.save(LogHelper.log(s.msg()));
    }

    @Override
    public boolean save(Student s) {
        s.setNo(IdWorker.getIdStr());
        return super.save(s) && logService.save(LogHelper.log(s.msg()));
    }

    @Transactional
    @Override
    public void del(IdsReq req) {
        List<Long> ids = req.getIds();
        if (orderMapper.exists(Wrappers.<Order>lambdaQuery().in(Order::getSid, ids).last(Const.LIMIT_1))) {
            throw new BusinessException("删除的学生存在订单, 删除失败");
        }
        logService.save(LogHelper.log("删除学生. 学生ID: ", ids));
        removeByIds(ids);
    }
}
