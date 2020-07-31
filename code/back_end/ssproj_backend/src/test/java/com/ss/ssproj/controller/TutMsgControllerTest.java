package com.ss.ssproj.controller;


import com.alibaba.fastjson.JSONArray;
import com.ss.ssproj.entity.*;
import com.ss.ssproj.interceptor.AuthenticationInterceptor;
import com.ss.ssproj.service.*;
import net.minidev.json.JSONObject;
import org.json.JSONString;
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
class TutMsgControllerTest {

    private MockMvc mockmvc;

    @Autowired
    private WebApplicationContext webapplicatioincontext;

    @Autowired
    private TutMsgController tutmsgcontroller;

    @MockBean
    private TutorService tutorservice;
    @MockBean
    private InsMessageService insmessageservice;
    @MockBean
    private ReadInsMsgService readinsmsgservice;
    @MockBean
    private StudentService studentservice;
    @MockBean
    private InstructService instructservice;

    @MockBean
    private AuthenticationInterceptor intereceptor;

    @BeforeEach
    public void setUp() throws Exception {
        mockmvc = MockMvcBuilders.webAppContextSetup(webapplicatioincontext).build();

        Mockito.when(tutorservice.findAll()).thenAnswer(
                new Answer<List<Tutor>>(){
                    @Override
                    public List<Tutor> answer(InvocationOnMock invocation){
                        List<Tutor> tut_list = new ArrayList<>();
                        tut_list.add(new Tutor(1, "tut1", "uid1", "name1"));
                        tut_list.add(new Tutor(2, "tut2", "uid2", "name2"));
                        tut_list.add(new Tutor(3, "tut3", "uid3", "name3"));
                        return tut_list;
                    }
                }
        );
        Mockito.when(insmessageservice.saveOrUpdate(Mockito.any())).thenAnswer(
                new Answer<InsMessage>(){
                    @Override
                    public InsMessage answer(InvocationOnMock invocation){
                        return invocation.getArgument(0);
                    }
                }
        );
        Mockito.when(readinsmsgservice.save(Mockito.any())).thenAnswer(
                new Answer<ReadInsMsg>(){
                    @Override
                    public ReadInsMsg answer(InvocationOnMock invocation){
                        return invocation.getArgument(0);
                    }
                }
        );
        Mockito.when(readinsmsgservice.findAllByMsgid(Mockito.anyInt())).thenAnswer(
                new Answer<List<ReadInsMsg>>(){
                    @Override
                    public List<ReadInsMsg> answer(InvocationOnMock invocation){
                        List<ReadInsMsg> res = new ArrayList<>();
                        ReadInsMsg msg1 = new ReadInsMsg(1, "sid1", 1, invocation.getArgument(0));
                        ReadInsMsg msg2 = new ReadInsMsg(3, "sid2", 0, invocation.getArgument(0));
                        res.add(msg1);
                        res.add(msg2);
                        return res;
                    }
                }
        );
        Mockito.when(studentservice.findDistinctByStudentId(Mockito.anyString())).thenAnswer(
                new Answer<Student>(){
                    @Override
                    public Student answer(InvocationOnMock invocation){
                        Student stu = new Student(1, invocation.getArgument(0), "uid", "name");
                        return stu;
                    }
                }
        );
        Mockito.when(instructservice.findAllByTutorid(Mockito.anyString())).thenAnswer(
                new Answer<List<Instruct>>(){
                    @Override
                    public List<Instruct> answer(InvocationOnMock invocation){
                        List<Instruct> res = new ArrayList<>();
                        Instruct ins1 = new Instruct(1, "sid1", invocation.getArgument(0));
                        Instruct ins2 = new Instruct(2, "sid2", invocation.getArgument(0));
                        res.add(ins1);
                        res.add(ins2);
                        return res;
                    }
                }
        );

        Mockito.when(insmessageservice.findAllByTutorid(Mockito.anyString())).thenAnswer(
                new Answer<List<InsMessage>>(){
                    @Override
                    public List<InsMessage> answer(InvocationOnMock invocation){
                        List<InsMessage> ret = new ArrayList<>();
                        ret.add(new InsMessage(1, invocation.getArgument(0), "title1", "content1", "time1"));
                        ret.add(new InsMessage(2, invocation.getArgument(0), "title2", "content2", "time2"));
                        return ret;
                    }
                }
        );
        Mockito.when(intereceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
    }

    @Test
    public void tutorsTest() throws Exception {
        JSONArray arr = new JSONArray();
        arr.add(new Tutor(1, "tut1", "uid1", "name1"));
        arr.add(new Tutor(2, "tut2", "uid2", "name2"));
        arr.add(new Tutor(3, "tut3", "uid3", "name3"));
        JSONArray exp = JSONArray.parseArray(arr.toJSONString());
        JSONArray res = JSONArray.parseArray(
                mockmvc.perform(MockMvcRequestBuilders.get("/api/user/tutors"))
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString()
        );

        assertEquals(exp, res);
    }

    @Test
    public void sendMsgTest() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("title", "tit1");
        map.put("content", "content1");
        map.put("time", "time1");
        map.put("tutorId", "tutId1");
        List<String> toIds = new ArrayList<>();
        toIds.add("sId1");
        toIds.add("sId2");
        map.put("toIds", toIds);
        String msgForm = JSONObject.toJSONString(map);
        System.out.println(msgForm);
        mockmvc.perform(MockMvcRequestBuilders.post("/api/tut/sendmsg")
                .contentType(MediaType.APPLICATION_JSON).content(msgForm))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(200));

    }

    @Test
    public void getMsgInfoTest() throws Exception {
        int msgid = 2;
        JSONArray res = JSONArray.parseArray(
                mockmvc.perform(MockMvcRequestBuilders.get("/api/tut/getmsginfo/"+msgid))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString()
        );

        String sid1 = "sid1";
        String sid2 = "sid2";
        Student stu1 = new Student(1, sid1, "uid", "name");
        stu1.setIfRead(1);
        Student stu2 = new Student(1, sid2, "uid", "name");
        stu2.setIfRead(0);
        JSONArray arr = new JSONArray();
        arr.add(stu2);
        arr.add(stu1);
        JSONArray exp = JSONArray.parseArray(arr.toJSONString());

        assertEquals(exp, res);
    }

    @Test
    public void getInsStudentsTest() throws Exception {
        String tutid = "tut3";
        JSONArray res = JSONArray.parseArray(
                mockmvc.perform(MockMvcRequestBuilders.get("/api/tut/students/"+tutid))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString()
        );

        String sid1 = "sid1";
        String sid2 = "sid2";
        Student stu1 = new Student(1, sid1, "uid", "name");
        Student stu2 = new Student(1, sid2, "uid", "name");
        JSONArray arr = new JSONArray();
        arr.add(stu1);
        arr.add(stu2);
        JSONArray exp = JSONArray.parseArray(arr.toJSONString());

        assertEquals(exp, res);
    }

    @Test
    public void getInsMagsTest() throws Exception {
        String tutid = "tut4";
        JSONArray res = JSONArray.parseArray(
                mockmvc.perform(MockMvcRequestBuilders.get("/api/tut/insmsgs/"+tutid))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString()
        );

        List<InsMessage> arr = new ArrayList<>();
        arr.add(new InsMessage(1, tutid, "title1", "content1", "time1"));
        arr.add(new InsMessage(2, tutid, "title2", "content2", "time2"));
    }
}