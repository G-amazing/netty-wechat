package com.gamazing.nettychat.entity;

import lombok.Data;

@Data
public class MyFriends {
    private String id;
    private String myUserId;
    private String myFriendUserId;
}