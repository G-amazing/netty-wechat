package com.gamazing.nettychat.vo;

import lombok.Data;

@Data
public class FriendRequestVO {
    private String sendUserId;
    private String sendUsername;
    private String sendFaceImage;
    private String sendNickname;
}