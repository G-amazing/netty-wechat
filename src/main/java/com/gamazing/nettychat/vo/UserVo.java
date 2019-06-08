package com.gamazing.nettychat.vo;

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

}
