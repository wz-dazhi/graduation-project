package com.graduation.controller.api;

import com.graduation.bean.Student;
import com.graduation.constant.Const;
import com.graduation.dto.req.IdsReq;
import com.graduation.dto.req.StudentPageReq;
import com.graduation.service.StudentService;
import com.wz.datasource.common.mybatisplus.model.IPage;
import com.wz.swagger.model.Result;
import com.wz.swagger.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(Const.API + "/student")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/page")
    public Result<IPage<Student>> page(@RequestBody StudentPageReq req) {
        return R.ok(studentService.page(req));
    }

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody Student student) {
        return R.ok(studentService.editor(student));
    }

    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody Student student) {
        return R.ok(studentService.editor(student));
    }

    @DeleteMapping("/del")
    public Result<Void> del(@Valid @RequestBody IdsReq req) {
        studentService.del(req);
        return R.ok();
    }
}
