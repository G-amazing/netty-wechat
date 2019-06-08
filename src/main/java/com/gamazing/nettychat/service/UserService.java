package com.gamazing.nettychat.service;

import com.gamazing.nettychat.dao.UserDao;
import com.gamazing.nettychat.entity.User;
import com.gamazing.nettychat.utils.IdWorker;
import com.gamazing.nettychat.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Service
public class UserService {
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private UserDao userDao;

    public User findOneByUserName(final String username) {
        return userDao.findOneByUserName(username);
    }

    public void signUp(final String username, final String password) {
        User user = new User();
        user.setUsername(username);
        user.setId(idWorker.nextId() + "");
        user.setNickname(username);
        user.setFaceImage("");
        user.setFaceImageBig("");
        // TODO: 2019/6/8 为每一个用户生成一个唯一的二维码
        user.setQrcode("");
        try {
            user.setPassword(MD5Utils.getMD5Str(password));
        } catch (Exception e) {
            e.printStackTrace();
        }
        userDao.create(user);
    }
}
