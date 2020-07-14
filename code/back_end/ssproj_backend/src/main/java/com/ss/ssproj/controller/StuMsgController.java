//学生查看所有信息
//学生将信息设置为已读
//信息搜索
package com.ss.ssproj.controller;

import com.ss.ssproj.entity.InsMessage;
import com.ss.ssproj.entity.JwcMessage;
import com.ss.ssproj.utils.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StuMsgController {

    @CrossOrigin
    @PostMapping(value = "api/stu/choosetutor")
    @ResponseBody
    public Result choose(@RequestBody ChooseForm chooseForm) {
        return new Result(400);
    }

    @CrossOrigin
    @PostMapping(value = "api/user/readmsg")
    @ResponseBody
    public Result readMsg(@RequestBody ReadMsgForm readMsgForm) {
        return new Result(200);
    }

    @CrossOrigin
    @ResponseBody
    @GetMapping(value = "api/stu/insmsg/{studentId}")
    public List<InsMessage> getInsMsg(@PathVariable("studentId") String studentId) {
        List<InsMessage> ret = new ArrayList<>();
        return ret;
    }
}
