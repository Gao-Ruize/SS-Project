//用户登录时用于判定用户身份
//用户注册时用于将其学号与微信号绑定
package com.ss.ssproj.controller;
import com.ss.ssproj.entity.Tutor;
import com.ss.ssproj.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {
    @Autowired
    TutorService tutorService;

    @CrossOrigin
    @GetMapping(value = "api/text1")
    @ResponseBody
    public List<Tutor> test() {
        List<Tutor> ret = tutorService.findAll();
        return ret;
    }
}
