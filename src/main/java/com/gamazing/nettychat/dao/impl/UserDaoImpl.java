package com.gamazing.nettychat.dao.impl;

import com.gamazing.nettychat.common.dao.BaseDao;
import com.gamazing.nettychat.dao.UserDao;
import com.gamazing.nettychat.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User findOneByUserName(final String username) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", username);
        return super.getSqlSession().selectOne(super.getStatement("findOneByUserName"), map);
    }

    @Override
    public void create(final User user) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("username", user.getUsername());
        map.put("password", user.getPassword());
        map.put("faceImage", user.getFaceImage());
        map.put("faceImageBig", user.getFaceImageBig());
        map.put("nickname", user.getNickname());
        map.put("qrcode", user.getQrcode());
        map.put("cid", user.getCid());
        super.getSqlSession().insert(super.getStatement("create"), map);
    }
}
