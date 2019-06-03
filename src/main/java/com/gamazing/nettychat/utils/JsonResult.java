package com.gamazing.nettychat.utils;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JsonResult<T> {
    // 响应业务状态
    private Integer code;
    // 响应消息
    private String msg;
    // 响应中的数据
    private T data;
}
