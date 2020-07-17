package com.ss.ssproj.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ss.ssproj.entity.JwcMessage;
import com.ss.ssproj.entity.ReadJwcMsg;
import com.ss.ssproj.service.JwcMessageService;
import com.ss.ssproj.service.ReadJwcMsgService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
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

    @Autowired
    JwcMsgController jwcMsgController;

    @BeforeEach
    public void beforeEachTest() {
        mockmvc = MockMvcBuilders.webAppContextSetup(webapplicationcontext).build();
    }

    @BeforeEach
    public void setUp() {
        mockmvc = MockMvcBuilders.webAppContextSetup(webapplicationcontext).build();
        JwcMessage jm1 = new JwcMessage(1,"2020","t1","c1",0);
        JwcMessage jm2 = new JwcMessage(2,"2020","t2","c2",0);
        ReadJwcMsg rjT1 = new ReadJwcMsg(1,null,"t123",0,0,1);
        ReadJwcMsg rjT2 = new ReadJwcMsg(2,null,"t123",0,0,2);
        ReadJwcMsg rjS1 = new ReadJwcMsg(3,"s123",null,0,1,1);
        ReadJwcMsg rjS2 = new ReadJwcMsg(4,"s123",null,0,1,2);
        List<JwcMessage> jwcMessageList = new ArrayList<>();
        jwcMessageList.add(jm1);
        jwcMessageList.add(jm2);
        Mockito.when(jwcMessageService.findAll()).thenReturn(jwcMessageList);
        Mockito.when(readJwcMsgService.findDistinctByStudentidAndMsgid("s123",1)).thenReturn(rjS1);
        Mockito.when(readJwcMsgService.findDistinctByStudentidAndMsgid("s123",2)).thenReturn(rjS2);
        Mockito.when(readJwcMsgService.findDistinctByTutoridAndMsgid("t123",1)).thenReturn(rjT1);
        Mockito.when(readJwcMsgService.findDistinctByTutoridAndMsgid("t123",2)).thenReturn(rjT2);
    }

    @Test
    void jwcmsgs() throws Exception {
        String check = "[" +
                "{" +
                "\"id\":1," +
                "\"releasetime\":\"2020\"" +
                ",\"title\":\"t1\"" +
                ",\"content\":\"c1\"" +
                ",\"phase\":0" +
                ",\"ifRead\":0" +
                "}" +
                "," +
                "{\"id\":2," +
                "\"releasetime\":\"2020\"," +
                "\"title\":\"t2\"," +
                "\"content\":\"c2\"," +
                "\"phase\":0," +
                "\"ifRead\":0" +
                "}" +
                "]";
        String ret = mockmvc
                .perform(MockMvcRequestBuilders.get("/api/user/jwcmsgs"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(check,ret);
    }


    @Test
    void typejwcmsgs() throws Exception {
        String checkS = "[" +
                "{" +
                "\"id\":1," +
                "\"releasetime\":\"2020\"," +
                "\"title\":\"t1\"," +
                "\"content\":\"c1\"," +
                "\"phase\":0," +
                "\"ifRead\":0" +
                "}," +
                "{" +
                "\"id\":2," +
                "\"releasetime\":\"2020\"," +
                "\"title\":\"t2\"," +
                "\"content\":\"c2\"," +
                "\"phase\":0," +
                "\"ifRead\":0" +
                "}" +
                "]";
        String checkT = "[{\"id\":1,\"releasetime\":\"2020\",\"title\":\"t1\",\"content\":\"c1\",\"phase\":0,\"ifRead\":0},{\"id\":2,\"releasetime\":\"2020\",\"title\":\"t2\",\"content\":\"c2\",\"phase\":0,\"ifRead\":0}]";
        String retS = mockmvc
                .perform(MockMvcRequestBuilders.get("/api/user/typejwcmsg/S/s123"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(checkS, retS);
        String retT = mockmvc
                .perform(MockMvcRequestBuilders.get("/api/user/typejwcmsg/T/t123"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(checkT, retT);
    }
}
