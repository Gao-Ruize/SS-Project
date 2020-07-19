package com.ss.ssproj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.ssproj.entity.InsMessage;
import com.ss.ssproj.entity.JwcMessage;
import com.ss.ssproj.entity.Tutor;
import com.ss.ssproj.service.InsMessageService;
import com.ss.ssproj.service.JwcMessageService;
import com.ss.ssproj.service.TutorService;
import com.ss.ssproj.utils.MsgForm;
import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class AllMsgControllerTest {
    private
    MockMvc mockmvc;

    @Autowired
    private WebApplicationContext webapplicationcontext;

    @MockBean
    InsMessageService insMessageService;

    @MockBean
    JwcMessageService jwcMessageService;

    @MockBean
    TutorService tutorService;

    @Autowired
    AllMsgController allMsgController;

    private ObjectMapper om = new ObjectMapper();

    @BeforeEach
    public void beforeEachTest() {
        mockmvc = MockMvcBuilders.webAppContextSetup(webapplicationcontext).build();
    }

    @BeforeEach
    public void setUp() {
        mockmvc = MockMvcBuilders.webAppContextSetup(webapplicationcontext).build();
        JwcMessage jwcMessage1 = new JwcMessage(1,"2020j","tj","cj",0);
        InsMessage insMessage1 = new InsMessage(2,"tutor123","ti","ci","2020i");
        Tutor tutor1 = new Tutor(1,"tutor123","uid","n");
        Mockito.when(jwcMessageService.findById(1)).thenReturn(jwcMessage1);
        Mockito.when(insMessageService.findDistinctById(2)).thenReturn(insMessage1);
        Mockito.when(jwcMessageService.findById(3)).thenReturn(null);
        Mockito.when(insMessageService.findDistinctById(3)).thenReturn(null);
        Mockito.when(tutorService.findDistinctByTutorId("tutor123")).thenReturn(tutor1);

    }

    @Test
    public void getMsgDetail() throws Exception {
        MsgForm jwcCheck = new MsgForm("tj","cj","2020j","jwc",null,null,0,null);
        String jwcResult;
        jwcResult = mockmvc
                .perform(MockMvcRequestBuilders.get("/api/user/msgdetail/1/jwc"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        MsgForm jwcObject = JSONObject.parseObject(jwcResult, MsgForm.class);
        assertEquals(jwcCheck, jwcObject);

        MsgForm insCheck = new MsgForm("ti","ci","2020i","n",null,null,0,null);
        String insResult;
        insResult = mockmvc
                .perform(MockMvcRequestBuilders.get("/api/user/msgdetail/2/tutor"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        MsgForm insObject = JSONObject.parseObject(insResult, MsgForm.class);
        assertEquals(insCheck, insObject);

        MsgForm NullCheck = new MsgForm(null,null,null,null,null,null,0,null);
        String nullJwc;
        nullJwc = mockmvc
                .perform(MockMvcRequestBuilders.get("/api/user/msgdetail/3/tutor"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        MsgForm nullJwcObj = JSONObject.parseObject(nullJwc, MsgForm.class);
        assertEquals(NullCheck, nullJwcObj);

        String nullIns;
        nullIns = mockmvc
                .perform(MockMvcRequestBuilders.get("/api/user/msgdetail/3/jwc"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        MsgForm nullInsObj = JSONObject.parseObject(nullIns, MsgForm.class);
        assertEquals(NullCheck, nullInsObj);
    }
}
