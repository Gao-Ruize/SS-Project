package com.ss.ssproj.controller;

import com.ss.ssproj.entity.Tutor;
import com.ss.ssproj.service.*;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
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
import java.util.List;

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

//    @Autowired
//    private TutorService tutorservice;
//    @Autowired
//    private InsMessageService insmessageservice;
//    @Autowired
//    private ReadInsMsgService readinsmsgservice;
//    @Autowired
//    private StudentService studentservice;
//    @Autowired
//    private InstructService instructservice;


    List<Tutor> tut_list = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        mockmvc = MockMvcBuilders.webAppContextSetup(webapplicatioincontext).build();

        Tutor tut1 = new Tutor(1, "tut1", "uid1", "name1");
        Tutor tut2 = new Tutor(2, "tut2", "uid2", "name2");
        Tutor tut3 = new Tutor(3, "tut3", "uid3", "name3");
        this.tut_list.clear();
        this.tut_list.add(tut1);
        this.tut_list.add(tut2);
        this.tut_list.add(tut3);

        Mockito.when(tutorservice.findAll()).thenReturn(tut_list);

    }

    @Test
    public void tutorsTest() throws Exception {
//        JSONArray array = new JSONArray();
//        array.add(tut_list);
//        String jsonString = array.toJSONString();
        JSONArray obj = new JSONArray();
        for(int i = 0; i < tut_list.size(); ++i){
            obj.add(tut_list.get(i));
        }
        String exp = obj.toJSONString();
        String result = mockmvc.perform(MockMvcRequestBuilders.get("/api/user/tutors"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        //assertEquals(exp, result);
    }

    @Test
    public void sendMsgTest() {
    }

    @Test
    public void getMsgInfoTest() {
    }

    @Test
    public void getInsStudentsTest() {
    }

    @Test
    public void getInsMagsTest() {
    }
}