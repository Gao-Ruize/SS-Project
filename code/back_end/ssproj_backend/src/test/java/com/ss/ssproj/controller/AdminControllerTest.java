package com.ss.ssproj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ss.ssproj.entity.*;
import com.ss.ssproj.interceptor.AuthenticationInterceptor;
import com.ss.ssproj.service.*;
import com.ss.ssproj.utils.JwcMsgCacu;
import com.ss.ssproj.utils.JwcMsgCacuDetail;
import com.ss.ssproj.utils.Result;
import com.ss.ssproj.utils.StuInfo;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class AdminControllerTest {
    private
    MockMvc mockmvc;

    @Autowired
    private WebApplicationContext webapplicationcontext;

    @MockBean
    private StudentService studentservice;

    @MockBean
    private TutorService tutorservice;

    @MockBean
    private InstructService instructservice;

    @MockBean
    private JwcMessageService jwcmessageservice;

    @MockBean
    private ReadJwcMsgService readjwcmsgservice;

    @MockBean
    private AdminService adminservice;

    @MockBean
    private TokenService tokenservice;

    @MockBean
    private AuthenticationInterceptor intereceptor;

    @BeforeEach
    public void setUp() throws Exception {
        mockmvc = MockMvcBuilders.webAppContextSetup(webapplicationcontext).build();
        Student stu1 = new Student(1, "student1", "uid1", "sname1");
        Student stu2 = new Student(2, "student2", "uid2", "sname2");
        Student stu3 = new Student(3, "student3", "uid3", "sname3");

        Mockito.when(studentservice.findAll()).thenAnswer(
                new Answer<List<Student>>(){
                    @Override
                    public List<Student> answer(InvocationOnMock invocation){
                        List<Student> ret = new ArrayList<>();
                        ret.add(stu1);
                        ret.add(stu2);
                        ret.add(stu3);
                        return ret;
                    }
                }
        );
        Mockito.when(instructservice.findDistinctByStudentid(Mockito.anyString())).thenAnswer(
                new Answer<Instruct>(){
                    @Override
                    public Instruct answer(InvocationOnMock invocation){
                        if(invocation.getArgument(0).equals("student1") || invocation.getArgument(0).equals("student2")){
                            return new Instruct(1, invocation.getArgument(0), "tutorid");
                        }else if(invocation.getArgument(0).equals("student_that_exist")){
                            return new Instruct(1, "studentid", "tutorid");
                        }else{
                            return null;
                        }
                    }
                }
        );
        Mockito.when(tutorservice.findDistinctByTutorId(Mockito.anyString())).thenAnswer(
                new Answer<Tutor>(){
                    @Override
                    public Tutor answer(InvocationOnMock invocation){
                        return new Tutor(1, "tutor", "uid", "tutorname");
                    }
                }
        );
        Mockito.when(tutorservice.findAll()).thenAnswer(
                new Answer<List<Tutor>>(){
                    @Override
                    public List<Tutor> answer(InvocationOnMock invocation){
                        List<Tutor> ret = new ArrayList<>();
                        ret.add(new Tutor(1, "tutorid1", "uid1", "tutorname1"));
                        ret.add(new Tutor(2, "tutorid2", "uid2", "tutorname2"));
                        ret.add(new Tutor(3, "tutorid3", "uid3", "tutorname3"));
                        return ret;
                    }
                }
        );
        Mockito.when(jwcmessageservice.findAll()).thenAnswer(
                new Answer<List<JwcMessage>>(){
                    @Override
                    public List<JwcMessage> answer(InvocationOnMock invocation){
                        List<JwcMessage> ret = new ArrayList<>();
                        for(int i = 0; i < 64; ++i){
                            ret.add(new JwcMessage(i, "releasetime"+i, "title"+i, "content"+i, i%4));
                        }
                        return ret;
                    }
                }
        );
        Mockito.when(readjwcmsgservice.findAllByIfstudentAndIfreadAndMsgid(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt())).thenAnswer(
                new Answer<List<ReadJwcMsg>>(){
                    @Override
                    public List<ReadJwcMsg> answer(InvocationOnMock invocation){
                        List<ReadJwcMsg> ret = new ArrayList<>();
                        int num = invocation.getArgument(2);
                        if(invocation.getArgument(0).equals(0)){
                            if(invocation.getArgument(1).equals(0))
                                num *= 1;
                            else
                                num *= 2;
                        }else{
                            if(invocation.getArgument(1).equals(0))
                                num *= 3;
                            else
                                num *= 4;
                        }
                        for(int i = 0; i < num; ++i){
                            ret.add(new ReadJwcMsg(i, "studentid", "tutorid", 0, 0, i));
                        }
                        return ret;
                    }
                }
        );
        Mockito.when(jwcmessageservice.findById(Mockito.anyInt())).thenAnswer(
                new Answer<JwcMessage>(){
                    @Override
                    public JwcMessage answer(InvocationOnMock invocation){
                        int param = invocation.getArgument(0);
                        return new JwcMessage(param, "releasetime"+param, "title"+param, "content"+param, param%4);
                    }
                }
        );
        Mockito.when(studentservice.findDistinctByStudentId(Mockito.anyString())).thenAnswer(
                new Answer<Student>(){
                    @Override
                    public Student answer(InvocationOnMock invocation){
                        return new Student(1, invocation.getArgument(0), "uid", "studentname");
                    }
                }
        );
        Mockito.when(tutorservice.findDistinctByTutorId(Mockito.anyString())).thenAnswer(
                new Answer<Tutor>(){
                    @Override
                    public Tutor answer(InvocationOnMock invocation){
                        return new Tutor(1, invocation.getArgument(0), "uid", "tutorname");
                    }
                }
        );
        Mockito.when(adminservice.findDistinctByUserid(Mockito.anyString())).thenAnswer(
                new Answer<Admin>(){
                    @Override
                    public Admin answer(InvocationOnMock invocation){
                        if(invocation.getArgument(0).equals("correctId")){
                            System.out.println("correctID");
                            return new Admin(1, "correctName", "correctPSW", "correctId");
                        }else{
                            return null;
                        }
                    }
                }
        );


        Mockito.when(intereceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
    }

    @Test
    public void adminGetStudents() throws Exception {
        String str = mockmvc.perform(MockMvcRequestBuilders.get("/api/admin/students"))
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();

        List<StuInfo> get = JSON.parseObject(str, new TypeReference<List<StuInfo>>(){});
        List<StuInfo> exp = new ArrayList<>();
        exp.add(new StuInfo(1, "student1", "uid1", "sname1", "tutorid", "tutorname"));
        exp.add(new StuInfo(2, "student2", "uid2", "sname2", "tutorid", "tutorname"));
        exp.add(new StuInfo(3, "student3", "uid3", "sname3", null, null));
        System.out.println(get.get(2).toString());
        System.out.println(exp.get(2).toString());
        System.out.println(str);
        assertEquals(exp, get);
    }

    @Test
    public void adminGetTutors() throws Exception {
        String str = mockmvc.perform(MockMvcRequestBuilders.get("/api/admin/tutors"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Tutor> get = JSON.parseObject(str, new TypeReference<List<Tutor>>(){});
        List<Tutor> exp = new ArrayList<>();
        exp.add(new Tutor(1, "tutorid1", "uid1", "tutorname1"));
        exp.add(new Tutor(2, "tutorid2", "uid2", "tutorname2"));
        exp.add(new Tutor(3, "tutorid3", "uid3", "tutorname3"));
        assertEquals(exp, get);
    }

    @Test
    public void calculatedJwcMsg() throws Exception {
        String str = mockmvc.perform(MockMvcRequestBuilders.get("/api/admin/jwcmsgs"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List<JwcMsgCacu> get = JSON.parseObject(str, new TypeReference<List<JwcMsgCacu>>(){});
        List<JwcMsgCacu> exp = new ArrayList<>();
        for(int i = 0; i < 64; ++i){
            exp.add(new JwcMsgCacu(i, "title"+i, "releasetime"+i, i%4, 2*i, 3*i, 4*i, 7*i));
        }
        assertEquals(exp, get);
    }

    @Test
    public void unbindTutor() throws Exception {
        mockmvc.perform(MockMvcRequestBuilders.post("/api/admin/unbindtutor")
                .contentType(MediaType.APPLICATION_JSON).content("student_that_exist"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(200));
        mockmvc.perform(MockMvcRequestBuilders.post("/api/admin/unbindtutor")
                .contentType(MediaType.APPLICATION_JSON).content("student_not_exist"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(400));
    }

    @Test
    public void getJwcMsgCacuDetail() throws Exception {
        for(int msgid = 1; msgid < 32; ++msgid){
            String str = mockmvc.perform(MockMvcRequestBuilders.get("/api/admin/jwcmsgdetail/"+msgid))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
            JwcMsgCacuDetail get = JSON.parseObject(str, new TypeReference<JwcMsgCacuDetail>(){});
            List<Student> students = new ArrayList<>();
            List<Tutor> tutors = new ArrayList<>();
            for(int i = 0; i < msgid*3; ++i){
                students.add(new Student(1, "studentid", "uid", "studentname"));
            }
            for(int i = 0; i < msgid; ++i){
                tutors.add(new Tutor(1, "tutorid", "uid", "tutorname"));
            }
            JwcMsgCacuDetail exp = new JwcMsgCacuDetail(msgid, "title"+msgid, "content"+msgid, "releasetime"+msgid, students, tutors);
            assertEquals(exp, get);
        }
    }

    @Test
    public void adminLogin() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("realId", "correctId");
        map.put("openId", "correctPSW");
        String loginmsg = JSONObject.toJSONString(map);
        mockmvc.perform(MockMvcRequestBuilders.post("/api/admin/login")
                .contentType(MediaType.APPLICATION_JSON).content(loginmsg))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(200));

        map.clear();
        map.put("realId", "correctId");
        map.put("openId", "fakePSW");
        loginmsg = JSONObject.toJSONString(map);
        mockmvc.perform(MockMvcRequestBuilders.post("/api/admin/login")
                .contentType(MediaType.APPLICATION_JSON).content(loginmsg))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(400));

        map.clear();
        map.put("realId", "fakeId");
        map.put("openId", "fakePSW");
        loginmsg = JSONObject.toJSONString(map);
        mockmvc.perform(MockMvcRequestBuilders.post("/api/admin/login")
                .contentType(MediaType.APPLICATION_JSON).content(loginmsg))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(400));
    }

//    @Test
//    void adminRegister() {
//    }
}