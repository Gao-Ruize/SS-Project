//向老师和学生提供所有的教务处信息
//并更新阅读情况
package com.ss.ssproj.controller;

import com.ss.ssproj.entity.JwcMessage;
import com.ss.ssproj.service.JwcMessageService;
import com.ss.ssproj.utils.MsgForm;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JwcMsgController {
    @Autowired
    JwcMessageService jwcMessageService;

    @CrossOrigin
    @GetMapping(value = "api/user/jwcmsgs")
    @ResponseBody
    public List<JwcMessage> jwcmsgs() {
        List<JwcMessage> ret = new ArrayList<>();
        return ret;
    }
}
