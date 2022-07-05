package com.graduation.controller.api;

import com.graduation.bean.User;
import com.graduation.constant.Const;
import com.graduation.service.UserService;
import com.wz.swagger.model.Result;
import com.wz.swagger.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Const.API + "/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    //@PostMapping("/login")
    @GetMapping("/login")
    public Result<Void> login(User u) {
        userService.login(u);
        return R.ok();
    }

    @GetMapping("/quit")
    public Result<Void> quit() {
        userService.quit();
        return R.ok();
    }

}
