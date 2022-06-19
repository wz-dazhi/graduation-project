package com.graduation.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.wz.datasource.mybatisplus.model.BaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @projectName: graduation-project
 * @package: com.graduation.bean
 * @className: Bean
 * @description:
 * @author: zhi
 * @date: 2022/6/18
 * @version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Bean extends BaseBean {

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 是否删除: 0-否, 1-是
     */
    @TableField("del")
    private Boolean del;

    @Override
    public String toString() {
        return super.toString();
    }
}
