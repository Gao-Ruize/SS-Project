//向老师和学生提供所有的教务处信息
//并更新阅读情况
package com.ss.ssproj.controller;

import com.ss.ssproj.entity.JwcMessage;
import com.ss.ssproj.service.JwcMessageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class JwcMsgController {
    @Autowired
    JwcMessageService jwcMessageService;

    @CrossOrigin
    @GetMapping(value = "api/test4")
    @ResponseBody
    public JwcMessage test3() {
        JwcMessage ret = jwcMessageService.findById(1);
        return ret;
    }
}
