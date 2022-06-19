package com.graduation.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @projectName: graduation-project
 * @package: com.graduation.controller.page
 * @className: OrderPage
 * @description:
 * @author: zhi
 * @date: 2022/6/16
 * @version: 1.0
 */
@Controller
public class OrderPage {
    @GetMapping({"/order/order.html"})
    public String order() {
        return "/order/order";
    }
}
