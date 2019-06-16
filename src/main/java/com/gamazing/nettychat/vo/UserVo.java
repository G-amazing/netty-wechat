package com.gamazing.nettychat.vo;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserVo {

    @Data
    public static class ReqSignup {
        @Size(min = 1, max = 200, message = "1000")
        @NotNull(message = "1000")
        private String username;
        @Size(min = 1, max = 200, message = "1001")
        @NotNull(message = "1001")
        private String password;
    }

    @Data
    public static class ReqImage {
        private String userId;
    }

    @Data
    @Builder
    public static class ResImage {
        // 原始图地址
        private String imageUrl;
        // 缩略图地址
        private String thumpImgUrl;
    }

}
