package com.graduation.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户表
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@TableName(value = "t_log")
public class Log extends Bean implements Serializable {

    /**
     * 操作人ID
     */
    @TableField(value = "operator_id")
    private Long operatorId;
    private transient String operatorName;

    /**
     * 操作信息
     */
    @TableField(value = "msg")
    private String msg;

    @Override
    public String toString() {
        return super.toString();
    }
}