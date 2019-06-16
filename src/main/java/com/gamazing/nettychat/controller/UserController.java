package com.gamazing.nettychat.controller;

import com.gamazing.nettychat.common.enums.ResultCode;
import com.gamazing.nettychat.entity.User;
import com.gamazing.nettychat.service.UserService;
import com.gamazing.nettychat.utils.*;
import com.gamazing.nettychat.vo.UserVo;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private Validator validator;
    @Autowired
    FastFileStorageClient storageClient;
    @Autowired
    private ThumbImageConfig thumbImageConfig;

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

    @PostMapping("/uploadFile")
    public JsonResult<UserVo.ResImage> registerOrLogin(@RequestParam("image") MultipartFile file) throws Exception {

        if (file.isEmpty()) {
            return JsonResult.<UserVo.ResImage>builder()
                    .status(ResultCode.USER_IMAGE_EMPTY.getStatus())
                    .message(ResultCode.USER_IMAGE_EMPTY.getMessage())
                    .build();
        }

        //源文件名称
        final String originalFileName = file.getOriginalFilename();
        if (StringUtils.isBlank(originalFileName)) {
            return JsonResult.<UserVo.ResImage>builder()
                    .status(ResultCode.USER_IMAGE_EMPTY.getStatus())
                    .message(ResultCode.USER_IMAGE_EMPTY.getMessage())
                    .build();
        }

        // 获取文件后缀名并判断文件类型
        final String suffix = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
        if (!FileUtils.IMAGE_EXTENSIONS.contains(suffix)) {
            return JsonResult.<UserVo.ResImage>builder()
                    .status(ResultCode.IMAGE_FORMAT_ERROR.getStatus())
                    .message(ResultCode.IMAGE_FORMAT_ERROR.getMessage())
                    .build();
        }

        // 使用fastDFS上传文件
        StorePath storePath = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), suffix, null);
        // 原始图url
        String imageUrl = "http://192.168.146.128:88/" + storePath.getFullPath();
        // 缩略图rul
        String thumpImgUrl = "http://192.168.146.128:88/" + thumbImageConfig.getThumbImagePath(storePath.getFullPath());

        return JsonResult.<UserVo.ResImage>builder()
                .status(ResultCode.SUCCESS.getStatus())
                .message(ResultCode.SUCCESS.getMessage())
                .data(
                        UserVo.ResImage.builder()
                                .imageUrl(imageUrl)
                                .thumpImgUrl(thumpImgUrl)
                                .build())
                .build();
    }
}
