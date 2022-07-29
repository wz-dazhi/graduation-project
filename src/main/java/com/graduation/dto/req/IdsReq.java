package com.graduation.dto.req;

import com.wz.common.util.JsonUtil;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @projectName: graduation-project
 * @package: com.graduation.dto.req
 * @className: IdsReq
 * @description:
 * @author: yue
 * @date: 2022/6/19
 * @version: 1.0
 */
@Data
public class IdsReq implements Serializable {

    @NotNull(message = "{common.ids.NotNull}")
    @Size(min = 1, message = "{common.ids.Size}")
    private List<Long> ids;

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
