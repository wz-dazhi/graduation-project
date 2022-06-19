package com.graduation.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @projectName: graduation-project
 * @package: com.graduation.controller.page
 * @className: StudentPage
 * @description:
 * @author: zhi
 * @date: 2022/6/16
 * @version: 1.0
 */
@Controller
public class StudentPage {
    @GetMapping({"/student/student.html"})
    public String student() {
        return "student/student";
    }
}
