//用户登录时用于判定用户身份
//用户注册时用于将其学号与微信号绑定
package com.ss.ssproj.controller;
import com.ss.ssproj.entity.Student;
import com.ss.ssproj.entity.Tutor;
import com.ss.ssproj.service.StudentService;
import com.ss.ssproj.service.TutorService;
import com.ss.ssproj.utils.LoginMsg;
import com.ss.ssproj.utils.RegisterForm;
import com.ss.ssproj.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginController {
    @Autowired
    TutorService tutorService;
    @Autowired
    StudentService studentService;

    @CrossOrigin
    @PostMapping(value = "api/user/login")
    @ResponseBody
    public LoginMsg login(@RequestBody String code) {
        //u_id 的获取
        String u_id = code;
        LoginMsg ret = new LoginMsg();
        Student student = studentService.findDistinctByUid(u_id);
        if(student == null) {
            Tutor tutor = tutorService.findDistinctByUid(u_id);
            if(tutor == null)
            {
                ret.setType("N");
            }
            else {
                ret.setRealId(tutor.getTutorid());
                ret.setType("T");
            }
        } else {
            ret.setRealId(student.getStudentid());
            ret.setType("S");
        }
        return ret;
    }

    //返回200为学生 201为老师 300为错误
    @CrossOrigin
    @PostMapping(value = "api/user/bind")
    @ResponseBody
    public Result bind(@RequestBody RegisterForm loginForm) {
        String real_id = loginForm.getRealId();
        String code = loginForm.getCode();
        String type = loginForm.getType();
        //通过code获取u_id
        String u_id = code;
        if(type.equals("1")) {
            //为学生
            //判断该学号是否被注册过
            Student tmpS = studentService.findDistinctByUid(u_id);
            if(tmpS == null) {
                //若没有则获取该生姓名
                Student student = new Student();
                student.setStudentid(real_id);
                student.setUid(u_id);
                student.setStudentname("name");
                studentService.saveOrUpdate(student);
                return new Result(200);
            }
        }
        if(type.equals("2")) {
            //判断是否被注册过
            Tutor tmpT = tutorService.findDistinctByUid(u_id);
            if(tmpT == null) {
                Tutor tutor = new Tutor();
                tutor.setTutorid(real_id);
                tutor.setUid(u_id);
                tutor.setTutorname("name");
                tutorService.saveOrUpdate(tutor);
                return new Result(201);
            }
        }
        return new Result(300);
    }
}
