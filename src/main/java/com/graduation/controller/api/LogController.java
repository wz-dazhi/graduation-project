package com.graduation.controller.api;

import com.graduation.constant.Const;
import com.graduation.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Const.API + "/log")
@AllArgsConstructor
public class LogController {
    private final LogService logService;

}
