package com.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.graduation.bean.Category;
import com.graduation.dto.req.CategoryPageReq;
import org.apache.ibatis.annotations.Param;

public interface CategoryMapper extends BaseMapper<Category> {

    IPage<Category> page(@Param("req") CategoryPageReq req);

}