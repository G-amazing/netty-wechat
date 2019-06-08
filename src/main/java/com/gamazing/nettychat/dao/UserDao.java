package com.gamazing.nettychat.dao;

import com.gamazing.nettychat.entity.User;

public interface UserDao {
    User findOneByUserName(String username);
}
