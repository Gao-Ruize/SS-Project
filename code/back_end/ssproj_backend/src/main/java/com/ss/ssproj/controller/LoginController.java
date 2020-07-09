//用户登录时用于判定用户身份
//用户注册时用于将其学号与微信号绑定
package com.ss.ssproj.controller;
import com.ss.ssproj.entity.Student;
import com.ss.ssproj.entity.Tutor;
import com.ss.ssproj.service.StudentService;
import com.ss.ssproj.service.TutorService;
import com.ss.ssproj.utils.LoginForm;
import com.ss.ssproj.utils.LoginMsg;
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
    @PostMapping(value = "api/login")
    @ResponseBody
    public LoginMsg login(@RequestBody String code) {
        LoginMsg ret = new LoginMsg("","");
        //check in db
        //if student
        ret.setMsg("s");
        //if tutor
        ret.setMsg("t");
        String codeId = "codeid";
        ret.setCodeId(codeId);
        return ret;
    }

    //返回200为学生 201为老师 300为错误
    @CrossOrigin
    @PostMapping(value = "api/bind")
    @ResponseBody
    public Result bind(@RequestBody LoginForm loginForm) {
        String real_id = loginForm.getReal_id();
        String u_id = loginForm.getU_id();
        String type = loginForm.getType();
        if(type.equals("1")) {
            //判断该学号是否被注册过
            //若没有则获取该生姓名
            Student student = new Student();
            student.setStudent_id(real_id);
            student.setU_id(u_id);
            //student.setStudent_name(name);
            studentService.saveOrUpdate(student);
            return new Result(200);
        }
        if(type.equals("2")) {
            //判断是否被注册过
            Tutor tutor = new Tutor();
            tutor.setTutor_id(real_id);
            tutor.setU_id(u_id);
            tutor.setTutor_name("name");
            tutorService.saveOrUpdate(tutor);
            return new Result(201);
        }
        return new Result(300);
    }
}
