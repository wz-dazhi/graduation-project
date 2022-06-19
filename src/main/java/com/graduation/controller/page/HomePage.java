package com.graduation.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @projectName: graduation-project
 * @package: com.graduation.controller.page
 * @className: HomePage
 * @description:
 * @author: zhi
 * @date: 2022/6/14
 * @version: 1.0
 */
@Controller
public class HomePage {

    @GetMapping({"/home.html"})
    public String home() {
        return "home";
    }

    @GetMapping({"/statistics.html"})
    public String statistics() {
        return "statistics";
    }

}
