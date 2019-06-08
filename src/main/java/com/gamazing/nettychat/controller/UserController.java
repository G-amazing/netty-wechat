package com.gamazing.nettychat.controller;

import com.gamazing.nettychat.entity.User;
import com.gamazing.nettychat.service.UserService;
import com.gamazing.nettychat.utils.IdWorker;
import com.gamazing.nettychat.utils.JsonResult;
import com.gamazing.nettychat.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private IdWorker idWorker;

    @PostMapping("/registerOrLogin")
    public JsonResult<String> registerOrLogin(@RequestBody User user) throws Exception{

        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            return JsonResult.<String>builder()
                    .code(1)
                    .msg("用户名或密码不能为空...")
                    .build();
        }

        // 判断用户名是否存在，如果存在就登录，如果不存在则注册
        User userDB = userService.findOneByUserName(user.getUsername());
        if (userDB == null) {
            // 注册
            user.setNickname(user.getUsername());
            user.setFaceImage("");
            user.setFaceImageBig("");
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
            //userService.save(user);
        } else {
            // 登陆
            if (!MD5Utils.getMD5Str(user.getPassword()).equals(userDB.getPassword())) {
                return JsonResult.<String>builder()
                        .code(1)
                        .msg("用户名或密码不正确...")
                        .build();
            }
        }
        return JsonResult.<String>builder()
                .code(0)
                .msg("成功")
                .build();
    }
}
