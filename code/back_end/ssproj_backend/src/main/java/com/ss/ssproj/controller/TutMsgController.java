//教师查看发送给学生的信息
//教师查看信息阅读情况
//教师给学生发送信息
//搜索
package com.ss.ssproj.controller;

import com.ss.ssproj.entity.InsMessage;
import com.ss.ssproj.entity.Instruct;
import com.ss.ssproj.entity.Student;
import com.ss.ssproj.entity.Tutor;
import com.ss.ssproj.service.InstructService;
import com.ss.ssproj.service.TutorService;
import com.ss.ssproj.utils.MsgForm;
import com.ss.ssproj.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TutMsgController {
    @Autowired
    TutorService tutorService;

    @CrossOrigin
    @GetMapping(value = "api/user/tutors")
    @ResponseBody
    public List<Tutor> tutors() {
        return tutorService.findAll();
    }

    @CrossOrigin
    @PostMapping(value = "api/tut/sendmsg")
    @ResponseBody
    public Result sendMsg(@RequestBody MsgForm msgForm) {
        return new Result(200);
    }

    @CrossOrigin
    @GetMapping(value = "api/tut/getmsginfo/{msgid}")
    @ResponseBody
    public List<Student> getMsgInfo(@PathVariable("msgid") String msgId) {
        List<Student> ret = new ArrayList<>();
        return ret;
    }

}
