package com.ss.ssproj.controller;

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
        String jwcForm = "{\"title\":\"tj\",\"content\":\"cj\",\"time\":\"2020j\",\"senderName\":\"jwc\",\"msgType\":null,\"tutorId\":null,\"msgId\":0,\"toIds\":null}";
        String jwcResult;
        jwcResult = mockmvc
                .perform(MockMvcRequestBuilders.get("/api/user/msgdetail/1/jwc"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        JwcMessage x = JSONObject.parseObject(jwcResult,JwcMessage.class);
        assertEquals(jwcForm,jwcResult);

        String insForm = "{\"title\":\"ti\",\"content\":\"ci\",\"time\":\"2020i\",\"senderName\":\"n\",\"msgType\":null,\"tutorId\":null,\"msgId\":0,\"toIds\":null}";
        String insResult;
        insResult = mockmvc
                .perform(MockMvcRequestBuilders.get("/api/user/msgdetail/2/tutor"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(insForm,insResult);

        String nullCheck = "{\"title\":null,\"content\":null,\"time\":null,\"senderName\":null,\"msgType\":null,\"tutorId\":null,\"msgId\":0,\"toIds\":null}";
        String nullJwc;
        nullJwc = mockmvc
                .perform(MockMvcRequestBuilders.get("/api/user/msgdetail/3/tutor"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(nullCheck, nullJwc);

        String nullIns;
        nullIns = mockmvc
                .perform(MockMvcRequestBuilders.get("/api/user/msgdetail/3/jwc"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(nullCheck, nullIns);
    }
}
