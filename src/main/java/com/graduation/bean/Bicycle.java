package com.graduation.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 单车分类表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "t_bicycle")
public class Bicycle extends Bean implements Serializable {
    /**
     * 单车分类
     */
    @TableField(value = "cid")
    private Long cid;

    /**
     * 单车名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 单车图片
     */
    @TableField(value = "img")
    private String img;

    /**
     * 入库时间
     */
    @TableField(value = "in_time")
    private LocalDateTime inTime;

    /**
     * 状态: 1-未借,2-已借,3-需维修,4-报废
     */
    @TableField(value = "`state`")
    private Integer state;

    @Override
    public String toString() {
        return super.toString();
    }
}