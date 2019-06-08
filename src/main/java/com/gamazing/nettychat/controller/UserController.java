package com.gamazing.nettychat.controller;

import com.gamazing.nettychat.common.enums.ResultCode;
import com.gamazing.nettychat.entity.User;
import com.gamazing.nettychat.service.UserService;
import com.gamazing.nettychat.utils.IdWorker;
import com.gamazing.nettychat.utils.JsonResult;
import com.gamazing.nettychat.utils.MD5Utils;
import com.gamazing.nettychat.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
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
    private Validator validator;

    @PostMapping("/registerOrLogin")
    public JsonResult<String> registerOrLogin(@RequestBody UserVo.ReqSignup request, BindingResult b) throws Exception{

        validator.validate(request, b);
        if (b.hasErrors()) {
            for (ObjectError e : b.getAllErrors()) {
                final ResultCode code = ResultCode.getCode(Integer.parseInt(e.getDefaultMessage()));
                return JsonResult.<String>builder()
                        .status(code.getStatus())
                        .message(code.getMessage())
                        .build();
            }
        }
        // 判断用户名是否存在，如果存在就登录，如果不存在则注册
        User userDB = userService.findOneByUserName(request.getUsername());
        if (userDB == null) {
            // 注册
            userService.signUp(request.getUsername(), request.getPassword());
        } else {
            // 登陆
            if (!MD5Utils.getMD5Str(request.getPassword()).equals(userDB.getPassword())) {
                return JsonResult.<String>builder()
                        .status(ResultCode.USERNAME_OR_PASSWORD_ERROR.getStatus())
                        .message(ResultCode.USERNAME_OR_PASSWORD_ERROR.getMessage())
                        .build();
            }
        }
        return JsonResult.<String>builder()
                .status(ResultCode.SUCCESS.getStatus())
                .message(ResultCode.SUCCESS.getMessage())
                .build();
    }
}
