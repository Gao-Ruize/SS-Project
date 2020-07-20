//教师查看发送给学生的信息
//教师查看信息阅读情况
//教师给学生发送信息
//搜索
package com.ss.ssproj.controller;

import com.ss.ssproj.entity.*;
import com.ss.ssproj.service.*;
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

    @Autowired
    InsMessageService insMessageService;

    @Autowired
    ReadInsMsgService readInsMsgService;

    @Autowired
    StudentService studentService;

    @Autowired
    InstructService instructService;

    //选导师时展示所有导师的信息
    @CrossOrigin
    @GetMapping(value = "api/user/tutors")
    @ResponseBody
    public List<Tutor> tutors() {
        return tutorService.findAll();
    }

    //导师给学生发送通知
    //每次处理
    @CrossOrigin
    @PostMapping(value = "api/tut/sendmsg")
    @ResponseBody
    public Result sendMsg(@RequestBody MsgForm msgForm) {
        List<String> receivers = msgForm.getToIds();
        if(receivers == null)
            return new Result(400);
        String title = msgForm.getTitle();
        String content = msgForm.getContent();
        String time = msgForm.getTime();
        String tutorId = msgForm.getTutorId();

        //将信息存入InsMessage
        InsMessage insMessage = new InsMessage();
        insMessage.setTitle(title);
        insMessage.setContent(content);
        insMessage.setTutorid(tutorId);
        insMessage.setReleasetime(time);
        InsMessage rec = insMessageService.saveOrUpdate(insMessage);
        int newId = rec.getId();
        //给每个学生发信息

        for(String item : receivers) {
            ReadInsMsg readInsMsg = new ReadInsMsg();
            readInsMsg.setIfread(0);
            readInsMsg.setMsgid(newId);
            readInsMsg.setStudentid(item);
            readInsMsgService.save(readInsMsg);
        }
        return new Result(200);
    }

    //老师查看某一通知学生的阅读情况
    @CrossOrigin
    @GetMapping(value = "api/tut/getmsginfo/{msgid}")
    @ResponseBody
    public List<Student> getMsgInfo(@PathVariable("msgid") int msgId) {
        List<Student> ret = new ArrayList<>();
        List<ReadInsMsg> readInsMsgs = readInsMsgService.findAllByMsgid(msgId);
        for(ReadInsMsg item : readInsMsgs) {
            String studentId = item.getStudentid();
            Student student = studentService.findDistinctByStudentId(studentId);
            if(student != null) {
                student.setIfRead(item.getIfread());
                ret.add(student);
            }
        }
        return ret;
    }

    @CrossOrigin
    @GetMapping(value = "api/tut/students/{tutid}")
    @ResponseBody
    public List<Student> getInsStudents(@PathVariable("tutid") String tutid) {
        List<Instruct> instructs = instructService.findAllByTutorid(tutid);
        List<Student> students = new ArrayList<>();
        for(Instruct item : instructs) {
            String studentId = item.getStudentid();
            Student student = studentService.findDistinctByStudentId(studentId);
            if(student != null) {
                students.add(student);
            }
        }
        return students;
    }

    @CrossOrigin
    @GetMapping(value = "api/tut/insmsgs/{tutid}")
    @ResponseBody
    public List<InsMessage> getInsMags(@PathVariable("tutid") String tutid) {
        return insMessageService.findAllByTutorid(tutid);
    }

}
