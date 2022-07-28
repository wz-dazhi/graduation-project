package com.graduation.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @projectName: graduation-project
 * @package: com.graduation.controller.page
 * @className: ProfilePage
 * @description:
 * @author: yue
 * @date: 2022/6/17
 * @version: 1.0
 */
@Controller
public class ProfilePage {

    @GetMapping({"/profile/profile.html"})
    public String profile() {
        return "profile/profile";
    }

}
