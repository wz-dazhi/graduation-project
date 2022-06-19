package com.graduation.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @projectName: graduation-project
 * @package: com.graduation.controller.page
 * @className: SystemPage
 * @description:
 * @author: zhi
 * @date: 2022/6/17
 * @version: 1.0
 */
@Controller
public class SystemPage {

    @GetMapping({"/system/user.html"})
    public String bicycle() {
        return "system/user";
    }

    @GetMapping({"/system/log.html"})
    public String bicycleClassify() {
        return "system/log";
    }

}
