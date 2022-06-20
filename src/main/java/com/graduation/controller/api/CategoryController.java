package com.graduation.controller.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.graduation.bean.Category;
import com.graduation.constant.Const;
import com.graduation.dto.req.CategoryPageReq;
import com.graduation.dto.req.IdsReq;
import com.graduation.service.CategoryService;
import com.wz.swagger.model.Result;
import com.wz.swagger.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(Const.API + "/category")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/page")
    public Result<IPage<Category>> page(@RequestBody CategoryPageReq req) {
        return R.ok(categoryService.page(req));
    }

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody Category category) {
        return R.ok(categoryService.editor(category));
    }

    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody Category category) {
        return R.ok(categoryService.editor(category));
    }

    @DeleteMapping("/del")
    public Result<Void> del(@Valid @RequestBody IdsReq req) {
        categoryService.del(req);
        return R.ok();
    }

}
