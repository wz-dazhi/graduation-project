package com.graduation.dto.req;

import com.wz.common.util.JsonUtil;
import com.wz.datasource.mybatisplus.model.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @projectName: graduation-project
 * @package: com.graduation.dto
 * @className: BasePageReq
 * @description:
 * @author: zhi
 * @date: 2022/6/18
 * @version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BasePageReq<T> extends Page<T> {

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
