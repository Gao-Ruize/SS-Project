package com.ss.ssproj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ss.ssproj.entity.JwcMessage;
import com.ss.ssproj.entity.ReadJwcMsg;
import com.ss.ssproj.entity.Student;
import com.ss.ssproj.interceptor.AuthenticationInterceptor;
import com.ss.ssproj.service.JwcMessageService;
import com.ss.ssproj.service.ReadJwcMsgService;
import org.hibernate.annotations.Check;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class JwcMsgControllerTest {
    private
    MockMvc mockmvc;

    @Autowired
    private WebApplicationContext webapplicationcontext;

    @MockBean
    JwcMessageService jwcMessageService;

    @MockBean
    ReadJwcMsgService readJwcMsgService;

    @MockBean
    private AuthenticationInterceptor intereceptor;

    @Autowired
    JwcMsgController jwcMsgController;

    @BeforeEach
    public void beforeEachTest() {
        mockmvc = MockMvcBuilders.webAppContextSetup(webapplicationcontext).build();
    }

    @BeforeEach
    public void setUp() throws Exception {
        mockmvc = MockMvcBuilders.webAppContextSetup(webapplicationcontext).build();
        JwcMessage jm1 = new JwcMessage(1,"2020","t1","c1",0);
        JwcMessage jm2 = new JwcMessage(2,"2020","t2","c2",0);
        ReadJwcMsg rjT1 = new ReadJwcMsg(1,null,"t123",0,0,1);
        ReadJwcMsg rjT2 = new ReadJwcMsg(2,null,"t123",0,0,2);
        ReadJwcMsg rjS1 = new ReadJwcMsg(3,"s123",null,0,1,1);
        ReadJwcMsg rjS2 = new ReadJwcMsg(4,"s123",null,0,1,2);
//        List<JwcMessage> jwcMessageList = new ArrayList<>();
//        jwcMessageList.add(jm1);
//        jwcMessageList.add(jm2);
        Mockito.when(jwcMessageService.findAll()).thenAnswer(
                new Answer<List<JwcMessage>>(){
                    @Override
                    public List<JwcMessage> answer(InvocationOnMock invocation){
                        List<JwcMessage> ret = new ArrayList<>();
                        ret.add(jm1);
                        ret.add(jm2);
                        return ret;
                    }
                }
        );
        Mockito.when(readJwcMsgService.findDistinctByStudentidAndMsgid("s123",1)).thenReturn(rjS1);
        Mockito.when(readJwcMsgService.findDistinctByStudentidAndMsgid("s123",2)).thenReturn(rjS2);
        Mockito.when(readJwcMsgService.findDistinctByTutoridAndMsgid("t123",1)).thenReturn(rjT1);
        Mockito.when(readJwcMsgService.findDistinctByTutoridAndMsgid("t123",2)).thenReturn(rjT2);
        Mockito.when(intereceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
    }

    @Test
    void jwcmsgsTest() throws Exception {
        JwcMessage jm1 = new JwcMessage(1,"2020","t1","c1",0);
        jm1.setIfRead(0);
        JwcMessage jm2 = new JwcMessage(2,"2020","t2","c2",0);
        jm2.setIfRead(0);
        JSONArray arr1 = new JSONArray();
        arr1.add(jm1);
        arr1.add(jm2);
        JSONArray Arr1 = JSONArray.parseArray(arr1.toJSONString());
        String ret = mockmvc
                .perform(MockMvcRequestBuilders.get("/api/user/jwcmsgs"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        JSONArray ret1 = JSONArray.parseArray(ret);
        assertEquals(Arr1,ret1);
    }


    @Test
    void typejwcmsgsTest() throws Exception {
        JwcMessage checks1 = new JwcMessage(1,"2020","t1","c1",0);
        checks1.setIfRead(0);
        JwcMessage checks2 = new JwcMessage(2,"2020","t2","c2",0);
        checks2.setIfRead(0);
        JSONArray checks = new JSONArray();
        checks.add(checks1);
        checks.add(checks2);
        JSONArray CheckS = JSONArray.parseArray(checks.toJSONString());
        JSONArray CheckT = CheckS;
        String retS = mockmvc
                .perform(MockMvcRequestBuilders.get("/api/user/typejwcmsg/S/s123"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        JSONArray RetS = JSONArray.parseArray(retS);
        assertEquals(CheckS,RetS);
        String retT = mockmvc
                .perform(MockMvcRequestBuilders.get("/api/user/typejwcmsg/T/t123"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        JSONArray RetT = JSONArray.parseArray(retT);
        assertEquals(CheckT, RetT);
    }
}
