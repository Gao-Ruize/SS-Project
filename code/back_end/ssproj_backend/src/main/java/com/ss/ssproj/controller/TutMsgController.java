//教师查看发送给学生的信息
//教师查看信息阅读情况
//教师给学生发送信息
//搜索
package com.ss.ssproj.controller;

import com.ss.ssproj.entity.Instruct;
import com.ss.ssproj.service.InstructService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TutMsgController {
    @Autowired
    InstructService instructService;

    @CrossOrigin
    @GetMapping(value = "api/test2")
    @ResponseBody
    public List<Instruct> findAll() {
        List<Instruct> ret = instructService.findAll();
        return ret;
    }
}
