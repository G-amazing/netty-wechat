package com.gamazing.nettychat.entity;

import lombok.Data;

@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String faceImage;
    private String faceImageBig;
    private String nickname;
    private String qrcode;
    private String cid;
}