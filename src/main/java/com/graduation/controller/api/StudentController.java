package com.graduation.controller.api;

import com.graduation.constant.Const;
import com.graduation.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Const.API + "/student")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;
}
