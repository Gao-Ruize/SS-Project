//学生查看所有信息
//学生将信息设置为已读
//信息搜索
package com.ss.ssproj.controller;

import com.ss.ssproj.entity.*;
import com.ss.ssproj.service.InsMessageService;
import com.ss.ssproj.service.InstructService;
import com.ss.ssproj.service.ReadInsMsgService;
import com.ss.ssproj.service.ReadJwcMsgService;
import com.ss.ssproj.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StuMsgController {
    @Autowired
    InstructService instructService;
    @Autowired
    InsMessageService insMessageService;
    @Autowired
    ReadJwcMsgService readJwcMsgService;
    @Autowired
    ReadInsMsgService readInsMsgService;

    @CrossOrigin
    @PostMapping(value = "api/stu/choosetutor")
    @ResponseBody
    public Result choose(@RequestBody ChooseForm chooseForm) {
        String studentId = chooseForm.getStudentId();
        String tutorId = chooseForm.getTutorId();
        Instruct instruct = new Instruct();
        instruct.setStudentid(studentId);
        instruct.setTutorid(tutorId);
        instructService.saveOrUpdate(instruct);
        return new Result(200);
    }

    //修改msg的ifRead状态
    @CrossOrigin
    @PostMapping(value = "api/user/readmsg")
    @ResponseBody
    public Result readMsg(@RequestBody ReadMsgForm readMsgForm) {
        String userId = readMsgForm.getUserId();
        String type = readMsgForm.getType();
        String userType = readMsgForm.getUserType();
        int msgId = readMsgForm.getMsgId();
        if(type.equals("tutor")) {
            //userType必定为S
            ReadInsMsg readInsMsg = readInsMsgService.findDistinctByStudentidAndMsgid(userId, msgId);
            readInsMsg.setIfread(1);
            readInsMsgService.save(readInsMsg);
            return new Result(200);
        } else if(type.equals("jwc")) {
            if(userType.equals("S")) {
                ReadJwcMsg readJwcMsg = readJwcMsgService.findDistinctByStudentidAndMsgid(userId, msgId);
                readJwcMsg.setIfread(1);
                readJwcMsgService.saveOrUpdate(readJwcMsg);
            } else if(userType.equals("T")) {
                ReadJwcMsg readJwcMsg = readJwcMsgService.findDistinctByTutoridAndMsgid(userId, msgId);
                readJwcMsg.setIfread(1);
                readJwcMsgService.saveOrUpdate(readJwcMsg);
            }
            return new Result(200);
        }
        return new Result(400);
    }

    //查看自己导师的所有信息
    @CrossOrigin
    @ResponseBody
    @GetMapping(value = "api/stu/insmsg/{studentId}")
    public List<InsMessage> getInsMsg(@PathVariable("studentId") String studentId) {
        List<ReadInsMsg> readInsMsgs = readInsMsgService.findAllByStudentid(studentId);
        List<InsMessage> ret = new ArrayList<>();
        for(ReadInsMsg item : readInsMsgs) {
            int msgid = item.getMsgid();
            InsMessage tmp = insMessageService.findDistinctById(msgid);
            ret.add(tmp);
        }
        return ret;
    }
}
