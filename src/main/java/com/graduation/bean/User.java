package com.graduation.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "t_user")
public class User extends Bean implements Serializable {
    /**
     * 姓名
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 账号
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    private String password;

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
     * 登陆时间
     */
    @TableField(value = "login_time")
    private LocalDateTime loginTime;

    @Override
    public String toString() {
        return super.toString();
    }
}