package com.graduation.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 学生表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "t_student")
public class Student extends Bean implements Serializable {
    /**
     * 编号
     */
    @TableField(value = "`no`")
    private String no;

    /**
     * 姓名
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 性别: 1-女, 2-男
     */
    @TableField(value = "sex")
    private Short sex;
    private transient String sexDesc;

    /**
     * 身份证
     */
    @TableField(value = "id_card")
    private String idCard;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 院系
     */
    @TableField(value = "faculty")
    private String faculty;

    /**
     * 专业
     */
    @TableField(value = "major")
    private String major;

    @Override
    public String toString() {
        return super.toString();
    }
}