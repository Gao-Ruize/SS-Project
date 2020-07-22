package com.ss.ssproj.controller;

import com.ss.ssproj.entity.*;
import com.ss.ssproj.service.*;
import com.ss.ssproj.utils.JwcMsgCacu;
import com.ss.ssproj.utils.JwcMsgCacuDetail;
import com.ss.ssproj.utils.Result;
import com.ss.ssproj.utils.StuInfo;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {
    @Autowired
    StudentService studentService;

    @Autowired
    TutorService tutorService;

    @Autowired
    InstructService instructService;

    @Autowired
    JwcMessageService jwcMessageService;

    @Autowired
    ReadJwcMsgService readJwcMsgService;

    @CrossOrigin
    @GetMapping(value = "api/admin/students")
    @ResponseBody
    public List<StuInfo> adminGetStudents() {
        List<StuInfo> ret = new ArrayList<>();
        List<Student> studentList = this.studentService.findAll();
        for(Student item : studentList) {
            String studentid = item.getStudentid();
            Instruct instruct = this.instructService.findDistinctByStudentid(studentid);
            if(instruct == null) {
                int id = item.getId();
                String studentname = item.getStudentname();
                String uid = item.getUid();
                StuInfo stuInfo = new StuInfo(id,studentid,uid,studentname,null,null);
                ret.add(stuInfo);
            } else {
                String tutorid = instruct.getTutorid();
                int id = item.getId();
                String studentname = item.getStudentname();
                String uid = item.getUid();
                Tutor tutor = this.tutorService.findDistinctByTutorId(tutorid);
                String tutorname = tutor.getTutorname();
                StuInfo stuInfo = new StuInfo(id,studentid,uid,studentname,tutorid,tutorname);
                ret.add(stuInfo);
            }
        }
        return ret;
    }

    @CrossOrigin
    @GetMapping(value = "api/admin/tutors")
    @ResponseBody
    public List<Tutor> adminGetTutors() {
        return this.tutorService.findAll();
    }

    @CrossOrigin
    @GetMapping(value = "api/admin/jwcmsgs")
    @ResponseBody
    public List<JwcMsgCacu> calculatedJwcMsg() {
        List<JwcMessage> jwcMessageList = this.jwcMessageService.findAll();
        List<JwcMsgCacu> ret = new ArrayList<>();
        for(JwcMessage item : jwcMessageList) {
            int id = item.getId();
            String title = item.getTitle();
            String releasetime = item.getReleasetime();
            int phase = item.getPhase();
            List<ReadJwcMsg> unreadTut = this.readJwcMsgService.findAllByIfstudentAndIfreadAndMsgid(0,0,id);
            List<ReadJwcMsg> readTut = this.readJwcMsgService.findAllByIfstudentAndIfreadAndMsgid(0,1,id);
            List<ReadJwcMsg> unreadStu = this.readJwcMsgService.findAllByIfstudentAndIfreadAndMsgid(1,0,id);
            List<ReadJwcMsg> readStu = this.readJwcMsgService.findAllByIfstudentAndIfreadAndMsgid(1,1,id);
            int utCount = unreadTut.size();
            int rtCount = readTut.size();
            int usCount = unreadStu.size();
            int rsCount = readStu.size();
            JwcMsgCacu jwcMsgCacu = new JwcMsgCacu(id,title,releasetime,phase,
                    rtCount,rtCount + utCount,rsCount,rsCount + usCount);
            ret.add(jwcMsgCacu);
        }
        return ret;
    }

    @CrossOrigin
    @PostMapping(value = "api/admin/unbindtutor")
    @ResponseBody
    public Result unbindTutor(@RequestBody String studentId) {
        Instruct instruct = instructService.findDistinctByStudentid(studentId);
        System.out.println(studentId);
        if(instruct == null)
            return new Result(400);
        else {
            this.instructService.deleteById(instruct.getId());
            return new Result(200);
        }
    }

    @CrossOrigin
    @GetMapping(value = "api/admin/jwcmsgdetail/{msgid}")
    @ResponseBody
    public JwcMsgCacuDetail getJwcMsgCacuDetail(@PathVariable("msgid") int msgid) {
        JwcMessage jwcMessage = this.jwcMessageService.findById(msgid);
        String title = jwcMessage.getTitle();
        String releasetime = jwcMessage.getReleasetime();
        String content = jwcMessage.getContent();
        List<Student> studentList = new ArrayList<>();
        List<Tutor> tutorList = new ArrayList<>();
        List<ReadJwcMsg> uS = this.readJwcMsgService.findAllByIfstudentAndIfreadAndMsgid(1,0,msgid);
        List<ReadJwcMsg> uT = this.readJwcMsgService.findAllByIfstudentAndIfreadAndMsgid(0,0,msgid);
        for(ReadJwcMsg item : uS) {
            String sid = item.getStudentid();
            Student student = this.studentService.findDistinctByStudentId(sid);
            if(student != null) {
                studentList.add(student);
            }
        }
        for(ReadJwcMsg item : uT) {
            String tid = item.getTutorid();
            Tutor tutor = this.tutorService.findDistinctByTutorId(tid);
            if(tutor != null) {
                tutorList.add(tutor);
            }
        }
        return new JwcMsgCacuDetail(msgid,title,content,releasetime,studentList,tutorList);
    }
}
