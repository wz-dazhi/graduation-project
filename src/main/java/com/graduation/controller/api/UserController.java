package com.graduation.controller.api;

import com.graduation.constant.Const;
import com.graduation.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Const.API + "/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
}
