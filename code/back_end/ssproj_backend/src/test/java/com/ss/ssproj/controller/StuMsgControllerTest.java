package com.ss.ssproj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ss.ssproj.entity.InsMessage;
import com.ss.ssproj.entity.Instruct;
import com.ss.ssproj.entity.ReadInsMsg;
import com.ss.ssproj.entity.ReadJwcMsg;
import com.ss.ssproj.interceptor.AuthenticationInterceptor;
import com.ss.ssproj.service.InsMessageService;
import com.ss.ssproj.service.InstructService;
import com.ss.ssproj.service.ReadInsMsgService;
import com.ss.ssproj.service.ReadJwcMsgService;
import net.minidev.json.JSONObject;
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
class StuMsgControllerTest {
    private MockMvc mockmvc;

    @Autowired
    private WebApplicationContext webapplicatioincontext;

    @Autowired
    private StuMsgController stumsgcontroller;

    @MockBean
    private InstructService instructservice;
    @MockBean
    private ReadJwcMsgService readjwcmsgservice;
    @MockBean
    private ReadInsMsgService readinsmsgservice;
    @MockBean
    private InsMessageService insmessageservice;

    @MockBean
    private AuthenticationInterceptor intereceptor;

    @BeforeEach
    public void setUp() throws Exception {
        mockmvc = MockMvcBuilders.webAppContextSetup(webapplicatioincontext).build();

        Mockito.when(instructservice.findDistinctByStudentidAndTutorid(Mockito.matches("sId1"), Mockito.matches("tutId"))).thenAnswer(
                new Answer<Instruct>(){
                    @Override
                    public Instruct answer(InvocationOnMock invocation){
                        Instruct ret = new Instruct(1, invocation.getArgument(0), invocation.getArgument(1));
                        return ret;
                    }
                }
        );
        Mockito.when(instructservice.findDistinctByStudentidAndTutorid(Mockito.matches("sId2"), Mockito.matches("tutId"))).thenAnswer(
                new Answer<Instruct>(){
                    @Override
                    public Instruct answer(InvocationOnMock invocation){
                        return null;
                    }
                }
        );
        Mockito.when(instructservice.findDistinctByStudentid(Mockito.matches("sId1"))).thenAnswer(
                new Answer<Instruct>(){
                    @Override
                    public Instruct answer(InvocationOnMock invocation){
                        Instruct ret = new Instruct(1, invocation.getArgument(0), "tutId");
                        return ret;
                    }
                }
        );
        Mockito.when(instructservice.findDistinctByStudentid(Mockito.matches("sId2"))).thenReturn(null);
        Mockito.when(instructservice.saveOrUpdate(Mockito.any())).thenAnswer(
                new Answer<Instruct>(){
                    @Override
                    public Instruct answer(InvocationOnMock invocation){
                        return invocation.getArgument(0);
                    }
                }
        );
        // msgId为2的按照信息不存在计
        Mockito.when(readjwcmsgservice.findDistinctByStudentidAndMsgid(Mockito.anyString(), Mockito.anyInt())).thenAnswer(
                new Answer<ReadJwcMsg>(){
                    @Override
                    public ReadJwcMsg answer(InvocationOnMock invocation){
                        if(invocation.getArgument(1).equals(2))
                            return null;
                        ReadJwcMsg ret = new ReadJwcMsg(1, invocation.getArgument(0), "", 0, 1, invocation.getArgument(1));
                        return ret;
                    }
                }
        );
        Mockito.when(readjwcmsgservice.findDistinctByTutoridAndMsgid(Mockito.anyString(), Mockito.anyInt())).thenAnswer(
                new Answer<ReadJwcMsg>(){
                    @Override
                    public ReadJwcMsg answer(InvocationOnMock invocation){
                        if(invocation.getArgument(1).equals(2))
                            return null;
                        ReadJwcMsg ret = new ReadJwcMsg(1, "", invocation.getArgument(0), 1, 0, invocation.getArgument(1));
                        return ret;
                    }
                }
        );
        Mockito.when(readinsmsgservice.findDistinctByStudentidAndMsgid(Mockito.anyString(), Mockito.anyInt())).thenAnswer(
                new Answer<ReadInsMsg>(){
                    @Override
                    public ReadInsMsg answer(InvocationOnMock invocation){
                        if(invocation.getArgument(1).equals(2))
                            return null;
                        ReadInsMsg ret = new ReadInsMsg(1, invocation.getArgument(0), 0, invocation.getArgument(1));
                        return ret;
                    }
                }
        );
        Mockito.when(readinsmsgservice.findAllByStudentid(Mockito.anyString())).thenAnswer(
                new Answer<List<ReadInsMsg>>(){
                    @Override
                    public List<ReadInsMsg> answer(InvocationOnMock invocation){
                        List<ReadInsMsg> ret = new ArrayList<>();
                        for(int i = 0; i < 32; i++){
                            ret.add(new ReadInsMsg(i+1, invocation.getArgument(0), i%2, i+1));
                        }
                        return ret;
                    }
                }
        );
        Mockito.when(insmessageservice.findDistinctById(Mockito.anyInt())).thenAnswer(
                new Answer<InsMessage>(){
                    @Override
                    public InsMessage answer(InvocationOnMock invocation){
                        int index = invocation.getArgument(0);
                        InsMessage ret = new InsMessage(index+1, "tutId", "title"+(index+1), "content"+(index+1), "time"+(index+1));
                        return ret;
                    }
                }
        );
        Mockito.when(readinsmsgservice.findAllByStudentidAndIfread(Mockito.anyString(), Mockito.anyInt())).thenAnswer(
                new Answer<List<ReadInsMsg>>(){
                    public List<ReadInsMsg> answer(InvocationOnMock invocation){
                        List<ReadInsMsg> ret = new ArrayList<>();
                        if(invocation.getArgument(1).equals(0)){
                            ret.add(new ReadInsMsg(1, invocation.getArgument(0), 0, 1));
                            ret.add(new ReadInsMsg(2, invocation.getArgument(0), 0, 2));
                        }else if(invocation.getArgument(1).equals(1)){
                            ret.add(new ReadInsMsg(1, invocation.getArgument(0), 1, 1));
                            ret.add(new ReadInsMsg(2, invocation.getArgument(0), 1, 2));
                            ret.add(new ReadInsMsg(3, invocation.getArgument(0), 1, 3));
                        }
                        return ret;
                    }
                }
        );
        Mockito.when(readjwcmsgservice.findAllByStudentidAndIfread(Mockito.anyString(), Mockito.anyInt())).thenAnswer(
                new Answer<List<ReadJwcMsg>>(){
                    public List<ReadJwcMsg> answer(InvocationOnMock invocation){
                        List<ReadJwcMsg> ret = new ArrayList<>();
                        if(invocation.getArgument(1).equals(0)){
                            ret.add(new ReadJwcMsg(1, invocation.getArgument(0), "tutId", 0, 1, 1));
                            ret.add(new ReadJwcMsg(2, invocation.getArgument(0), "tutId", 0, 1, 2));
                        }else if(invocation.getArgument(1).equals(1)){
                            ret.add(new ReadJwcMsg(1, invocation.getArgument(0), "tutId", 1, 1, 1));
                            ret.add(new ReadJwcMsg(2, invocation.getArgument(0), "tutId", 1, 1, 2));
                            ret.add(new ReadJwcMsg(3, invocation.getArgument(0), "tutId", 1, 1, 3));
                        }
                        return ret;
                    }
                }
        );
        Mockito.when(intereceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
    }

    @Test
    public void chooseTest() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("studentId", "sId1");
        map.put("tutorId", "tutId");
        String chooseForm = JSONObject.toJSONString(map);
        mockmvc.perform(MockMvcRequestBuilders.post("/api/stu/choosetutor")
                .contentType(MediaType.APPLICATION_JSON).content(chooseForm))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(400));

        map.clear();
        map.put("studentId", "sId2");
        map.put("tutorId", "tutId");
        chooseForm = JSONObject.toJSONString(map);
        mockmvc.perform(MockMvcRequestBuilders.post("/api/stu/choosetutor")
                .contentType(MediaType.APPLICATION_JSON).content(chooseForm))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(200));
    }

    @Test
    public void readMsgTest() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", "sId1");
        map.put("msgId", 1);
        map.put("type", "jwc");
        map.put("userType", "S");
        String readMsgForm = JSONObject.toJSONString(map);
        mockmvc.perform(MockMvcRequestBuilders.post("/api/user/readmsg")
                .contentType(MediaType.APPLICATION_JSON).content(readMsgForm))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(200));

        map.clear();
        map.put("userId", "tutId");
        map.put("msgId", 1);
        map.put("type", "jwc");
        map.put("userType", "T");
        readMsgForm = JSONObject.toJSONString(map);
        mockmvc.perform(MockMvcRequestBuilders.post("/api/user/readmsg")
                .contentType(MediaType.APPLICATION_JSON).content(readMsgForm))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(200));

        map.clear();
        map.put("userId", "sId2");
        map.put("msgId", 1);
        map.put("type", "tutor");
        map.put("userType", "S");
        readMsgForm = JSONObject.toJSONString(map);
        mockmvc.perform(MockMvcRequestBuilders.post("/api/user/readmsg")
                .contentType(MediaType.APPLICATION_JSON).content(readMsgForm))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(200));


        map.clear();
        map.put("userId", "sId1");
        map.put("msgId", 2);
        map.put("type", "jwc");
        map.put("userType", "S");
        readMsgForm = JSONObject.toJSONString(map);
        mockmvc.perform(MockMvcRequestBuilders.post("/api/user/readmsg")
                .contentType(MediaType.APPLICATION_JSON).content(readMsgForm))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(400));

        map.clear();
        map.put("userId", "tutId");
        map.put("msgId", 2);
        map.put("type", "jwc");
        map.put("userType", "T");
        readMsgForm = JSONObject.toJSONString(map);
        mockmvc.perform(MockMvcRequestBuilders.post("/api/user/readmsg")
                .contentType(MediaType.APPLICATION_JSON).content(readMsgForm))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(400));

        map.clear();
        map.put("userId", "sId2");
        map.put("msgId", 2);
        map.put("type", "tutor");
        map.put("userType", "S");
        readMsgForm = JSONObject.toJSONString(map);
        mockmvc.perform(MockMvcRequestBuilders.post("/api/user/readmsg")
                .contentType(MediaType.APPLICATION_JSON).content(readMsgForm))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(400));

        map.clear();
        map.put("userId", "sId2");
        map.put("msgId", 2);
        map.put("type", "other");
        map.put("userType", "S");
        readMsgForm = JSONObject.toJSONString(map);
        mockmvc.perform(MockMvcRequestBuilders.post("/api/user/readmsg")
                .contentType(MediaType.APPLICATION_JSON).content(readMsgForm))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(400));
    }

    @Test
    public void getInsMsg() throws Exception {
        String sId = "sId";
        JSONArray ret = JSONArray.parseArray(
                mockmvc.perform(MockMvcRequestBuilders.get("/api/stu/insmsg/"+sId))
                        .andExpect(status().isOk())

                        .andReturn().getResponse().getContentAsString()
        );

        JSONArray arr = new JSONArray();
        for(int i = 0; i < 32; i++){
            arr.add(new InsMessage(i+2, "tutId", "title"+(i+2), "content"+(i+2), "time"+(i+2)));
        }
        JSONArray exp = JSONArray.parseArray(arr.toJSONString());

        assertEquals(exp.size(), ret.size());
    }

    @Test
    public void unreadInsMsg() throws Exception {
        String sId = "sId";
        String ret =
                mockmvc.perform(MockMvcRequestBuilders.get("/api/stu/unreadinsmsg/"+sId))
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();
        assertEquals(ret, "2");
    }

    @Test
    public void unreadJwcMsg() throws Exception {
        String sId = "sId";
        String ret =
                mockmvc.perform(MockMvcRequestBuilders.get("/api/stu/unreadjwcmsg/"+sId))
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();
        assertEquals(ret, "2");
    }
}