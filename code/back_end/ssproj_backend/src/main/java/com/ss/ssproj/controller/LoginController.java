//用户登录时用于判定用户身份
//用户注册时用于将其学号与微信号绑定
package com.ss.ssproj.controller;
import com.ss.ssproj.annotation.AdminLoginToken;
import com.ss.ssproj.annotation.StudentLoginToken;
import com.ss.ssproj.annotation.TutorLoginToken;
import com.ss.ssproj.annotation.UserToken;
import com.ss.ssproj.entity.Student;
import com.ss.ssproj.entity.Tutor;
import com.ss.ssproj.service.StudentService;
import com.ss.ssproj.service.TokenService;
import com.ss.ssproj.service.TutorService;
import com.ss.ssproj.utils.LoginMsg;
import com.ss.ssproj.utils.RegisterForm;
import com.ss.ssproj.utils.Result;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ss.ssproj.utils.HttpRequest;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    TutorService tutorService;

    @Autowired
    StudentService studentService;

    @Autowired
    TokenService tokenService;


//    @Autowired
//    private HttpRequest httprequest;

    //测试用
    @UserToken
    @CrossOrigin
    @GetMapping(value = "api/user/hello")
    @ResponseBody
    public String helloU() {
        return "hello user";
    }

    //测试用
    @TutorLoginToken
    @CrossOrigin
    @GetMapping(value = "api/tut/hello")
    @ResponseBody
    public String helloT() {
        return "hello tutor";
    }

    //测试用
    @StudentLoginToken
    @CrossOrigin
    @GetMapping(value = "api/stu/hello")
    @ResponseBody
    public String helloS() {
        return "hello student";
    }

    @AdminLoginToken
    @CrossOrigin
    @GetMapping(value = "api/admin/hello")
    @ResponseBody
    public String helloA() {
        return "hello admin";
    }

    //测试用
    @CrossOrigin
    @GetMapping(value = "api/getToken/{rid}/{uid}")
    @ResponseBody
    public String getToken(@PathVariable("rid") String rid, @PathVariable("uid") String uid) {
        return this.tokenService.getToken(rid,uid);
    }


    @CrossOrigin
    @PostMapping(value = "api/user/login")
    @ResponseBody
    public LoginMsg login(@RequestBody String code) throws JSONException {
        // code为空时
        LoginMsg map = new LoginMsg();

        // 登录凭证不能为空
        if (code == null || code.length() == 0) {
            map.setRealId("code 不能为空");
            map.setType("err");
            map.setOpenId("0");
            return map;
        }

        // 小程序唯一标识 (在微信小程序管理后台获取) 此处修改成自己的id
        String wxspAppid = "wxfc660793593ad691";
        // 小程序的 app secret (在微信小程序管理后台获取) 此处修改成自己的secret
        String wxspSecret = "2a9fce2e91b62d6b069c5de6ee140b53";
        // 授权
        String grant_type = "authorization_code";

        // 向微信服务器 使用登录凭证 code 获取 session_key 和 openid
        // 请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type="
                + grant_type;
        // 发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        // 解析相应内容（转换成json对象）
        JSONObject json = new JSONObject(sr);
        // 出错处理：
        // int errcode = (int) json.get("errcode");
        if(json.has("errcode")){
            map.setRealId("code出错");
            map.setType("err");
            map.setOpenId("0");
            return map;
        }
        // 获取会话密钥（session_key）
        String session_key = json.get("session_key").toString();
        // 用户的唯一标识（openid）
        String openid = (String) json.get("openid");
        String rid = "";

        LoginMsg ret = new LoginMsg();
        ret.setOpenId(openid);
        Student student = this.studentService.findDistinctByUid(openid);
        if(student == null) {
            Tutor tutor = this.tutorService.findDistinctByUid(openid);
            if(tutor == null)
            {
                ret.setType("N");
            }
            else {
                rid = tutor.getTutorid();
                ret.setRealId(tutor.getTutorid());
                ret.setType("T");
            }
        } else {
            rid = student.getStudentid();
            ret.setRealId(student.getStudentid());
            ret.setType("S");
        }
        String token = "";
        token = this.tokenService.getToken(rid, openid);
        ret.setToken(token);
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
            //学生信息预先在db中存好
            //判断该学号是否被注册过
            Student tmpS = this.studentService.findDistinctByStudentId(real_id);
            if(tmpS == null) {
                return new Result(400);
            }
            if(tmpS.getUid() == null) {
                //若没有则获取该生姓名
                tmpS.setUid(u_id);
                this.studentService.saveOrUpdate(tmpS);
                return new Result(200);
            }
        }
        if(type.equals("2")) {
            //判断是否被注册过
            Tutor tmpT = this.tutorService.findDistinctByTutorId(real_id);
            if(tmpT == null) {
                return new Result(400);
            }
            if(tmpT.getUid() == null) {
                tmpT.setUid(u_id);
                this.tutorService.saveOrUpdate(tmpT);
                return new Result(201);
            }
        }
        return new Result(300);
    }
}
