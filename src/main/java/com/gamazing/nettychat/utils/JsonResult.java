package com.gamazing.nettychat.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult<T> {
    // 响应业务状态
    private Integer status;
    // 响应消息
    private String message;
    // 响应中的数据
    private T data;
}
