package com.graduation.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.bean.Category;
import com.graduation.dto.req.CategoryPageReq;
import com.graduation.dto.req.IdsReq;
import com.graduation.mapper.CategoryMapper;
import com.graduation.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public IPage<Category> page(CategoryPageReq req) {
        return baseMapper.page(req);
    }

    @Override
    public void del(IdsReq req) {
        this.removeByIds(req.getIds());
    }

}
