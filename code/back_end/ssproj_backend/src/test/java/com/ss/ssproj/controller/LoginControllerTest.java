package com.ss.ssproj.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ss.ssproj.entity.Student;
import com.ss.ssproj.entity.Tutor;
import com.ss.ssproj.service.StudentService;
import com.ss.ssproj.service.TutorService;
import com.ss.ssproj.utils.HttpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class LoginControllerTest {
    private MockMvc mockmvc;

    @Autowired
    private WebApplicationContext webapplicatioincontext;

    @Autowired
    private LoginController logincontroller;

//    @MockBean
//    private HttpRequest httprequest;
    @MockBean
    private StudentService studentservice;
    @MockBean
    private TutorService tutorservice;

    @BeforeEach
    public void setUp() {
        mockmvc = MockMvcBuilders.webAppContextSetup(webapplicatioincontext).build();

//        Mockito.when(httprequest.sendGet(Mockito.matches("https://api.weixin.qq.com/sns/jscode2session"), Mockito.anyString())).thenAnswer(
//                new Answer<String>(){
//                    @Override
//                    public String answer(InvocationOnMock invocation){
//                        String ret = new String();
//                        String params = invocation.getArgument(1);
//                        String err_param = "appid=wxfc660793593ad691"
//                                +"&secret=2a9fce2e91b62d6b069c5de6ee140b53"
//                                +"&js_code="+"codeErr"
//                                +"&grant_type=authorization_code";
//                        String S_param = "appid=wxfc660793593ad691"
//                                +"&secret=2a9fce2e91b62d6b069c5de6ee140b53"
//                                +"&js_code="+"codeS"
//                                +"&grant_type=authorization_code";
//                        String T_param = "appid=wxfc660793593ad691"
//                                +"&secret=2a9fce2e91b62d6b069c5de6ee140b53"
//                                +"&js_code="+"codeT"
//                                +"&grant_type=authorization_code";
//                        String N_param = "appid=wxfc660793593ad691"
//                                +"&secret=2a9fce2e91b62d6b069c5de6ee140b53"
//                                +"&js_code="+"codeN"
//                                +"&grant_type=authorization_code";
//
//                        Map<String, Object> map = new HashMap<>();
//                        if(params.equals(err_param)){
//                            map.put("errcode", "err");
//                            map.put("session_key", "Err_session_key");
//                            map.put("openid", "Err_openid");
//                        }else if(params.equals(S_param)){
//                            map.put("session_key", "S_session_key");
//                            map.put("openid", "S_openid");
//                        }else if(params.equals(T_param)){
//                            map.put("session_key", "T_session_key");
//                            map.put("openid", "T_openid");
//                        }else if(params.equals(N_param)){
//                            map.put("session_key", "N_session_key");
//                            map.put("openid", "N_openid");
//                        }
//                        ret = JSONObject.toJSONString(map);
//                        return ret;
//                    }
//                }
//        );
        Mockito.when(studentservice.findDistinctByUid(Mockito.anyString())).thenAnswer(
                new Answer<Student>(){
                    @Override
                    public Student answer(InvocationOnMock invocation){
                        if(invocation.getArgument(0).equals("S_openid")){
                            Student ret = new Student(1, "sId", invocation.getArgument(0), "sName");
                            return ret;
                        }else{
                            return null;
                        }
                    }
                }
        );
        Mockito.when(tutorservice.findDistinctByUid(Mockito.anyString())).thenAnswer(
                new Answer<Tutor>(){
                    @Override
                    public Tutor answer(InvocationOnMock invocation){
                        if(invocation.getArgument(0).equals("T_openid")){
                            Tutor ret = new Tutor(1, "tutId", invocation.getArgument(0), "tutName");
                            return ret;
                        }else{
                            return null;
                        }
                    }
                }
        );
        Mockito.when(studentservice.findDistinctByStudentId(Mockito.anyString())).thenAnswer(
                new Answer<Student>(){
                    @Override
                    public Student answer(InvocationOnMock invocation){
                        String param = invocation.getArgument(0);
                        if(param.equals("null")){
                            return null;
                        }else if(param.equals("sId_noBind")){
                            Student ret = new Student(1, param, null, "sName");
                            return ret;
                        }else{
                            Student ret = new Student(1, param, "uid", "sName");
                            return ret;
                        }
                    }
                }
        );
        Mockito.when(studentservice.saveOrUpdate(Mockito.any())).thenAnswer(
                new Answer<Student>(){
                    @Override
                    public Student answer(InvocationOnMock invocation){
                        return invocation.getArgument(0);
                    }
                }
        );
        Mockito.when(tutorservice.findDistinctByTutorId(Mockito.anyString())).thenAnswer(
                new Answer<Tutor>() {
                    @Override
                    public Tutor answer(InvocationOnMock invocation) {
                        String param = invocation.getArgument(0);
                        if(param.equals("null")){
                            return null;
                        }else if(param.equals("tutId_noBind")){
                            Tutor ret = new Tutor(1, param, null, "tutName");
                            return ret;
                        }else{
                            Tutor ret = new Tutor(1, param, "uid", "tutName");
                            return ret;
                        }
                    }
                }
        );
//        Mockito.when(tutorservice.saveOrUpdate(Mockito.any()));

    }

    @Test
    public void loginTest() throws Exception {
//        String codeErr = "codeErr";
//        String codeS = "codeS";
//        String codeT = "codeT";
//        String codeN = "codeN";
//        String Err_openid = "0";
//        String S_openid = "S_openid";
//        String T_openid = "T_openid";
//        String N_openid = "N_openid";
//        String Err_realId = "code出错";
//        String S_realId = "sId";
//        String T_realId = "tutId";
//
//        mockmvc.perform(MockMvcRequestBuilders.post("/api/user/login")
//                .contentType(MediaType.APPLICATION_JSON).content(codeErr))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("realId").value(Err_realId))
//                .andExpect(MockMvcResultMatchers.jsonPath("type").value("err"))
//                .andExpect(MockMvcResultMatchers.jsonPath("openId").value(Err_openid));
//
//        mockmvc.perform(MockMvcRequestBuilders.post("/api/user/login")
//                .contentType(MediaType.APPLICATION_JSON).content(codeS))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("realId").value(S_realId))
//                .andExpect(MockMvcResultMatchers.jsonPath("type").value("S"))
//                .andExpect(MockMvcResultMatchers.jsonPath("openId").value(S_openid));
//
//        mockmvc.perform(MockMvcRequestBuilders.post("/api/user/login")
//                .contentType(MediaType.APPLICATION_JSON).content(codeT))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("realId").value(T_realId))
//                .andExpect(MockMvcResultMatchers.jsonPath("type").value("T"))
//                .andExpect(MockMvcResultMatchers.jsonPath("openId").value(T_openid));
//
//        mockmvc.perform(MockMvcRequestBuilders.post("/api/user/login")
//                .contentType(MediaType.APPLICATION_JSON).content(codeN))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("type").value("N"))
//                .andExpect(MockMvcResultMatchers.jsonPath("openId").value(N_openid));

    }

    @Test
    public void bindTest() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "S_code");
        map.put("realId", "null");
        map.put("type", "1");
        String loginform = JSONObject.toJSONString(map);
        mockmvc.perform(MockMvcRequestBuilders.post("/api/user/bind")
                .contentType(MediaType.APPLICATION_JSON).content(loginform))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(400));

        map.clear();
        map.put("code", "S_code");
        map.put("realId", "sId_noBind");
        map.put("type", "1");
        loginform = JSONObject.toJSONString(map);
        mockmvc.perform(MockMvcRequestBuilders.post("/api/user/bind")
                .contentType(MediaType.APPLICATION_JSON).content(loginform))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(200));

        map.clear();
        map.put("code", "S_code");
        map.put("realId", "sId");
        map.put("type", "1");
        loginform = JSONObject.toJSONString(map);
        mockmvc.perform(MockMvcRequestBuilders.post("/api/user/bind")
                .contentType(MediaType.APPLICATION_JSON).content(loginform))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(300));

        map.clear();
        map.put("code", "T_code");
        map.put("realId", "null");
        map.put("type", "2");
        loginform = JSONObject.toJSONString(map);
        mockmvc.perform(MockMvcRequestBuilders.post("/api/user/bind")
                .contentType(MediaType.APPLICATION_JSON).content(loginform))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(400));

        map.clear();
        map.put("code", "T_code");
        map.put("realId", "tutId_noBind");
        map.put("type", "2");
        loginform = JSONObject.toJSONString(map);
        mockmvc.perform(MockMvcRequestBuilders.post("/api/user/bind")
                .contentType(MediaType.APPLICATION_JSON).content(loginform))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(201));

        map.clear();
        map.put("code", "T_code");
        map.put("realId", "tutId");
        map.put("type", "2");
        loginform = JSONObject.toJSONString(map);
        mockmvc.perform(MockMvcRequestBuilders.post("/api/user/bind")
                .contentType(MediaType.APPLICATION_JSON).content(loginform))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(300));
    }
}