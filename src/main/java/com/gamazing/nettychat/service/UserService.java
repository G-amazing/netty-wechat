package com.gamazing.nettychat.service;

import com.gamazing.nettychat.dao.UserDao;
import com.gamazing.nettychat.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User findOneByUserName(final String username) {
        return userDao.findOneByUserName(username);
    }
}
