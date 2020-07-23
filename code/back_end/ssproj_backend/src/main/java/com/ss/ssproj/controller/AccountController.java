//教师查看发送给学生的信息
//教师查看信息阅读情况
//教师给学生发送信息
//搜索
package com.ss.ssproj.controller;

import com.ss.ssproj.annotation.AdminLoginToken;
import com.ss.ssproj.entity.Student;
import com.ss.ssproj.entity.Tutor;
import com.ss.ssproj.service.StudentService;
import com.ss.ssproj.service.TutorService;
import com.ss.ssproj.utils.LoginMsg;
import com.ss.ssproj.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    TutorService tutorService;
    @Autowired
    StudentService studentService;

    //解除绑定
    @AdminLoginToken
    @CrossOrigin
    @PostMapping(value = "api/admin/unbind")
    @ResponseBody
    public Result unbind(@RequestBody LoginMsg unbindMsg) {
        String realId = unbindMsg.getRealId();
        String type = unbindMsg.getType();
        if(type.equals("1")) {
            //学生
            Student tmpS = this.studentService.findDistinctByStudentId(realId);
            System.out.println("tmpS.getUid(): "+tmpS.getUid());
            if(tmpS.getUid() == null) {
                System.out.println("return temS.getUid() == null");
                return new Result(400);
            } else {
                tmpS.setUid(null);
                this.studentService.saveOrUpdate(tmpS);
                System.out.println("return: tmpS.setUid(null)");
                return new Result(200);
            }
        } else
        if(type.equals("2")) {
            //导师
            Tutor tmpT = this.tutorService.findDistinctByTutorId(realId);
            if(tmpT.getUid() == null) {
                return new Result(400);
            } else {
                tmpT.setUid(null);
                this.tutorService.saveOrUpdate(tmpT);
                return new Result(200);
            }
        }
        return new Result(400);
    }
}
