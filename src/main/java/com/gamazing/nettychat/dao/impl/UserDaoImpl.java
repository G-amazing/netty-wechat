package com.gamazing.nettychat.dao.impl;

import com.gamazing.nettychat.common.BaseDao;
import com.gamazing.nettychat.dao.UserDao;
import com.gamazing.nettychat.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User findOneByUserName(String username) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", username);
        return super.getSqlSession().selectOne(super.getStatement("findOneByUserName"), map);
    }
}
