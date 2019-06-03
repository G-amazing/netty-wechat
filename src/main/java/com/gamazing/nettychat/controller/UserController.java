package com.gamazing.nettychat.controller;

import com.gamazing.nettychat.utils.IdWorker;
import com.gamazing.nettychat.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IdWorker idWorker;

    @PostMapping("/login")
    public JsonResult<String> login() {

        return null;
    }
}
