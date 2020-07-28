//学生查看所有信息
//学生将信息设置为已读
//信息搜索
package com.ss.ssproj.controller;

import com.ss.ssproj.annotation.StudentLoginToken;
import com.ss.ssproj.annotation.UserToken;
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

    @StudentLoginToken
    @CrossOrigin
    @PostMapping(value = "api/stu/choosetutor")
    @ResponseBody
    public Result choose(@RequestBody ChooseForm chooseForm) {
        String studentId = chooseForm.getStudentId();
        String tutorId = chooseForm.getTutorId();
        Instruct check = this.instructService.findDistinctByStudentid(studentId);
        if(check != null) {
            return new Result(400);
        }
        Instruct instruct = new Instruct();
        instruct.setStudentid(studentId);
        instruct.setTutorid(tutorId);
        this.instructService.saveOrUpdate(instruct);
        return new Result(200);
    }

    //修改msg的ifRead状态
    @UserToken
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
            ReadInsMsg readInsMsg = this.readInsMsgService.findDistinctByStudentidAndMsgid(userId, msgId);
            if(readInsMsg == null) {
                return new Result(400);
            }
            readInsMsg.setIfread(1);
            this.readInsMsgService.save(readInsMsg);
            return new Result(200);
        } else if(type.equals("jwc")) {
            if(userType.equals("S")) {
                ReadJwcMsg readJwcMsg = this.readJwcMsgService.findDistinctByStudentidAndMsgid(userId, msgId);
                if(readJwcMsg == null) {
                    return new Result(400);
                }
                readJwcMsg.setIfread(1);
                this.readJwcMsgService.saveOrUpdate(readJwcMsg);
            } else if(userType.equals("T")) {
                ReadJwcMsg readJwcMsg = this.readJwcMsgService.findDistinctByTutoridAndMsgid(userId, msgId);
                if(readJwcMsg == null) {
                    return new Result(400);
                }
                readJwcMsg.setIfread(1);
                this.readJwcMsgService.saveOrUpdate(readJwcMsg);
            }
            return new Result(200);
        }
        return new Result(400);
    }

    //查看自己导师的所有信息
    @StudentLoginToken
    @CrossOrigin
    @ResponseBody
    @GetMapping(value = "api/stu/insmsg/{studentId}")
    public List<InsMessage> getInsMsg(@PathVariable("studentId") String studentId) {
        List<ReadInsMsg> readInsMsgs = this.readInsMsgService.findAllByStudentid(studentId);
        List<InsMessage> ret = new ArrayList<>();
        for(ReadInsMsg item : readInsMsgs) {
            int msgid = item.getMsgid();
            InsMessage tmp = this.insMessageService.findDistinctById(msgid);
            int ifRead = item.getIfread();
            tmp.setIfRead(ifRead);
            ret.add(tmp);
        }
        //对消息进行排序，将未读消息放到前面
        List<InsMessage> sortRet = new ArrayList<>();
        for(int i = 0; i < ret.size(); ++ i) {
            InsMessage item = ret.get(i);
            if(item.getIfRead() == 0) {
                sortRet.add(item);
                ret.remove(i);
                i --;
            }
        }
        sortRet.addAll(ret);
        return sortRet;
    }

    //获取未读导师信息数量
    @StudentLoginToken
    @CrossOrigin
    @ResponseBody
    @GetMapping(value = "api/stu/unreadinsmsg/{studentId}")
    public int unreadInsMsg(@PathVariable("studentId") String studentId) {
        List<ReadInsMsg> readInsMsgs = this.readInsMsgService.findAllByStudentidAndIfread(studentId, 0);
        return readInsMsgs.size();
    }

    //获取未读教务处信息数量
    @StudentLoginToken
    @CrossOrigin
    @ResponseBody
    @GetMapping(value = "api/stu/unreadjwcmsg/{studentid}")
    public int unreadJwcMsg(@PathVariable("studentid") String studentid) {
        List<ReadJwcMsg> readJwcMsgs = this.readJwcMsgService.findAllByStudentidAndIfread(studentid, 0);
        return readJwcMsgs.size();
    }
}
