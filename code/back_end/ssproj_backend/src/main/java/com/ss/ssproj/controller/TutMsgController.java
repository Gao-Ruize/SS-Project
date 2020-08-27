//教师查看发送给学生的信息
//教师查看信息阅读情况
//教师给学生发送信息
//搜索
package com.ss.ssproj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ss.ssproj.annotation.TutorLoginToken;
import com.ss.ssproj.annotation.UserToken;
import com.ss.ssproj.entity.*;
import com.ss.ssproj.service.*;
import com.ss.ssproj.utils.HttpRequest;
import com.ss.ssproj.utils.MsgForm;
import com.ss.ssproj.utils.Result;
import com.ss.ssproj.utils.TemplateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    ReadJwcMsgService readJwcMsgService;

    //选导师时展示所有导师的信息
    @UserToken
    @CrossOrigin
    @GetMapping(value = "api/user/tutors")
    @ResponseBody
    public List<Tutor> tutors() {
        return this.tutorService.findAll();
    }

    //导师给学生发送通知
    //每次处理
    @TutorLoginToken
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
        InsMessage rec = this.insMessageService.saveOrUpdate(insMessage);
        int newId = rec.getId();
        //给每个学生发信息

        for(String item : receivers) {
            ReadInsMsg readInsMsg = new ReadInsMsg();
            readInsMsg.setIfread(0);
            readInsMsg.setMsgid(newId);
            readInsMsg.setStudentid(item);
            this.readInsMsgService.save(readInsMsg);
            //给学生发送信息
            Student student = this.studentService.findDistinctByStudentId(item);
            String uid = student.getUid();
            this.sendNotice(uid);
        }
        return new Result(200);
    }

    //老师查看某一通知学生的阅读情况
    @TutorLoginToken
    @CrossOrigin
    @GetMapping(value = "api/tut/getmsginfo/{msgid}")
    @ResponseBody
    public List<Student> getMsgInfo(@PathVariable("msgid") int msgId) {
        List<Student> ret = new ArrayList<>();
        List<ReadInsMsg> readInsMsgs = this.readInsMsgService.findAllByMsgid(msgId);
        for(ReadInsMsg item : readInsMsgs) {
            String studentId = item.getStudentid();
            Student student = this.studentService.findDistinctByStudentId(studentId);
            if(student != null) {
                student.setIfRead(item.getIfread());
                student.setReply(item.getReply());
                ret.add(student);
            }
        }
        //将未读排到前面
        List<Student> sortRet = new ArrayList<>();
        for(int i = 0;i < ret.size(); ++ i) {
            Student item = ret.get(i);
            if(item.getIfRead() == 0) {
                sortRet.add(item);
                ret.remove(i);
                i --;
            }
        }
        sortRet.addAll(ret);
        return sortRet;
    }

    //导师查看自己指导的所有学生
    @TutorLoginToken
    @CrossOrigin
    @GetMapping(value = "api/tut/students/{tutid}")
    @ResponseBody
    public List<Student> getInsStudents(@PathVariable("tutid") String tutid) {
        List<Instruct> instructs = this.instructService.findAllByTutorid(tutid);
        List<Student> students = new ArrayList<>();
        for(Instruct item : instructs) {
            String studentId = item.getStudentid();
            Student student = this.studentService.findDistinctByStudentId(studentId);
            if(student != null) {
                students.add(student);
            }
        }
        return students;
    }

    //导师查看自己发送的所有信息
    @TutorLoginToken
    @CrossOrigin
    @GetMapping(value = "api/tut/insmsgs/{tutid}")
    @ResponseBody
    public List<InsMessage> getInsMags(@PathVariable("tutid") String tutid) {
        return this.insMessageService.findAllByTutorid(tutid);
    }

    @TutorLoginToken
    @CrossOrigin
    @GetMapping(value = "api/tut/jwcmsgcount/{tutid}")
    @ResponseBody
    public int getJwcMsgCount(@PathVariable("tutid") String tutid) {
        List<ReadJwcMsg> count = this.readJwcMsgService.findAllByTutoridAndIfread(tutid, 0);
        return count.size();
    }

    //跨域为测试时使用，实际只需要在发送信息后调用，参数为学生的微信uid
    @CrossOrigin
    @GetMapping(value = "api/sendNotice")
    @ResponseBody
    public Result sendNotice(String uid) {
        //实际调用时删除，仅测试时使用
        uid = "oGHQL41GnIk7aXtTCALuwIkJLeXw";
        //参数uid为微信id
        //根据小程序的appId与appSecret
        String appId = "wxfc660793593ad691";
        String appSecret = "2a9fce2e91b62d6b069c5de6ee140b53";

        String params = "grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
        String url = "https://api.weixin.qq.com/cgi-bin/token" + "?" + params;
        RestTemplate restTemplate = new RestTemplate();
        //获取access_token
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        String retData = entity.getBody();
        JSONObject json1 = JSONObject.parseObject(retData);
        String access_token;
        if(json1.containsKey("access_token")) {
            access_token = json1.getString("access_token");
        } else return new Result(400);

        //模版id，根据实际模版id改变
        String templateId = "9nsC02qakcZ8FzRlXV5lLNOyuOkHSmNKnbDUDEUob0g";

        //调用发送请求接口
        JSONObject data = new JSONObject();

        //根据模版进行参数的组装
        //后续可以根据需求自主申请模版
        JSONObject thing2 = new JSONObject();
        JSONObject name1 = new JSONObject();
        thing2.put("value", "收到一条新信息，请及时查阅");
        name1.put("value", "导师");
        data.put("thing2", thing2);
        data.put("name1", name1);
        TemplateForm dataFrom = new TemplateForm(uid, templateId, data);

        String url2 = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + access_token;
        //调用微信提供的接口，发送消息
        ResponseEntity<String> postEntity = restTemplate.postForEntity(url2, dataFrom, String.class);
        return new Result(200);
    }

}
