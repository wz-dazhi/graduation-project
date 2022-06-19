package com.graduation.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "t_order")
public class Order extends Bean implements Serializable {
    /**
     * 学生ID
     */
    @TableField(value = "sid")
    private Long sid;

    /**
     * 单车ID
     */
    @TableField(value = "bid")
    private Long bid;

    /**
     * 借车时间
     */
    @TableField(value = "borrow_time")
    private LocalDateTime borrowTime;

    /**
     * 还车时间
     */
    @TableField(value = "return_time")
    private LocalDateTime returnTime;

    /**
     * 租金
     */
    @TableField(value = "real_rent")
    private BigDecimal realRent;

    /**
     * 押金
     */
    @TableField(value = "cash")
    private BigDecimal cash;

    /**
     * 状态: 1-未归还, 2-归还, 3-归还(需维修), 4-归还(报废)
     */
    @TableField(value = "state")
    private Integer state;

    @Override
    public String toString() {
        return super.toString();
    }
}