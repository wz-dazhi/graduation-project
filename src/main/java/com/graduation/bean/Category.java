package com.graduation.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 单车分类表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "t_category")
public class Category extends Bean implements Serializable {
    /**
     * 品牌名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 剩余数量
     */
    @TableField(value = "remain")
    private Integer remain;

    /**
     * 单车采购单价
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 租金,单位:元/小时
     */
    @TableField(value = "real_rent")
    private BigDecimal realRent;

    /**
     * 押金
     */
    @TableField(value = "cash")
    private BigDecimal cash;

    @Override
    public String toString() {
        return super.toString();
    }
}