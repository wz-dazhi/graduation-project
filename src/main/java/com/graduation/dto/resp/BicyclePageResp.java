package com.graduation.dto.resp;

import com.graduation.dto.Base;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @projectName: graduation-project
 * @package: com.graduation.dto.resp
 * @className: BicyclePageResp
 * @description:
 * @author: zhi
 * @date: 2022/6/19
 * @version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BicyclePageResp extends Base {

    @ApiModelProperty("分类ID")
    private Long cid;

    @ApiModelProperty("单车名称")
    private String name;

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("单车图片")
    private String img;

    @ApiModelProperty("入库时间")
    private LocalDateTime inTime;

    @ApiModelProperty("当前状态")
    private Integer state;

    @Override
    public String toString() {
        return super.toString();
    }
}
