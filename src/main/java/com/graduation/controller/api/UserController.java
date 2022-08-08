package com.graduation.controller.api;

import com.graduation.bean.User;
import com.graduation.constant.Const;
import com.graduation.dto.req.ChangePasswordReq;
import com.graduation.dto.req.IdsReq;
import com.graduation.dto.req.UserPageReq;
import com.graduation.service.UserService;
import com.wz.datasource.common.mybatisplus.model.IPage;
import com.wz.swagger.model.Result;
import com.wz.swagger.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(Const.API + "/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/page")
    public Result<IPage<User>> page(@RequestBody UserPageReq req) {
        return R.ok(userService.page(req));
    }

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody User student) {
        return R.ok(userService.editor(student));
    }

    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody User student) {
        return R.ok(userService.editor(student));
    }

    @DeleteMapping("/del")
    public Result<Void> del(@Valid @RequestBody IdsReq req) {
        userService.del(req);
        return R.ok();
    }

    @PostMapping("/change/pass")
    public Result<Boolean> changePassword(@Valid @RequestBody ChangePasswordReq req) {
        return R.ok(userService.changePassword(req));
    }

    @PostMapping("/login")
    public Result<User> login(@RequestBody User u) {
        return R.ok(userService.login(u));
    }

    @GetMapping("/quit")
    public Result<Void> quit() {
        userService.quit();
        return R.ok();
    }

}
