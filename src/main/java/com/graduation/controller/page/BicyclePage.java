package com.graduation.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @projectName: graduation-project
 * @package: com.graduation.controller.page
 * @className: BicyclePage
 * @description:
 * @author: zhi
 * @date: 2022/6/17
 * @version: 1.0
 */
@Controller
public class BicyclePage {

    @GetMapping({"/bicycle/bicycle-category.html"})
    public String bicycleClassify() {
        return "bicycle/bicycle-category";
    }

    @GetMapping({"/bicycle/bicycle.html"})
    public String bicycle() {
        return "bicycle/bicycle";
    }

}
