package com.gamazing.nettychat.vo;

import lombok.Data;

@Data
public class MyFriendsVO {
    private String friendUserId;
    private String friendUsername;
    private String friendFaceImage;
    private String friendNickname;
}