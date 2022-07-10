package com.graduation.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.bean.Bicycle;
import com.graduation.bean.Category;
import com.graduation.bean.Log;
import com.graduation.constant.Const;
import com.graduation.dto.req.CategoryPageReq;
import com.graduation.dto.req.IdsReq;
import com.graduation.mapper.BicycleMapper;
import com.graduation.mapper.CategoryMapper;
import com.graduation.service.CategoryService;
import com.graduation.service.LogService;
import com.graduation.util.LogHelper;
import com.wz.common.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    private final BicycleMapper bicycleMapper;
    private final LogService logService;

    @Override
    public IPage<Category> page(CategoryPageReq req) {
        return baseMapper.page(req);
    }

    @Transactional
    @Override
    public boolean editor(Category category) {
        this.verifyName(category.getName(), category.getId());
        Log l = LogHelper.log(category.msg());
        logService.save(l);
        if (category.getId() == null || category.getId() <= 0) {
            return this.save(category);
        }

        return this.updateById(category);
    }

    private void verifyName(String name, Long id) {
        if (lambdaQuery().eq(Category::getName, name).ne(id != null && id > 0, Category::getId, id).last(Const.LIMIT_1).exists()) {
            throw new BusinessException("分类名称已存在");
        }
    }

    @Transactional
    @Override
    public void del(IdsReq req) {
        List<Long> ids = req.getIds();
        for (Long id : ids) {
            if (bicycleMapper.exists(Wrappers.<Bicycle>lambdaQuery().eq(Bicycle::getCid, id).last(Const.LIMIT_1)))
                throw new BusinessException("当前分类[" + id + "]已绑定单车, 删除失败");
        }
        Log l = LogHelper.log("删除单车分类. 分类ID: ", ids);
        logService.save(l);
        this.removeByIds(ids);
    }

}
