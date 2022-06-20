package com.graduation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graduation.bean.Category;
import com.graduation.dto.req.CategoryPageReq;
import com.graduation.dto.req.IdsReq;

public interface CategoryService extends IService<Category> {

    IPage<Category> page(CategoryPageReq req);

    boolean editor(Category category);

    void del(IdsReq req);
}
