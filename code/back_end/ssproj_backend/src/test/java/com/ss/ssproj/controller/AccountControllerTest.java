package com.ss.ssproj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.ssproj.entity.Student;
import com.ss.ssproj.entity.Tutor;
import com.ss.ssproj.service.StudentService;
import com.ss.ssproj.service.TutorService;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.junit.jupiter.api.BeforeAll;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class AccountControllerTest {

    private MockMvc mockmvc;

    @Autowired
    private WebApplicationContext webapplicationcontext;

//    @Autowired
//    private TutorService tutorservice;
//    @Autowired
//    private StudentService studentservice;
    @MockBean
    private TutorService tutorservice;
    @MockBean
    private StudentService studentservice;

    @Autowired
    private AccountController accountcontroller;

    private ObjectMapper om = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockmvc = MockMvcBuilders.webAppContextSetup(webapplicationcontext).build();

        Student stu = new Student(1, "s1", "uid", "name1");
        Student stu_noUid = new Student(2, "s2", null, "name2");
        Mockito.when(studentservice.findDistinctByStudentId(Mockito.matches("s1"))).thenReturn(stu);
        Mockito.when(studentservice.findDistinctByStudentId(Mockito.matches("s2"))).thenReturn(stu_noUid);
        Mockito.when(studentservice.saveOrUpdate(Mockito.any())).thenAnswer(
                new Answer<Student>(){
                    @Override
                    public Student answer(InvocationOnMock invocation){
                        return invocation.getArgument(0);
                    }
                }
        );

        Tutor tut = new Tutor(1, "tut1", "uid", "name1");
        Tutor tut_noUid = new Tutor(2, "tut2", null, "name2");
        Mockito.when(tutorservice.findDistinctByTutorId(Mockito.matches("tut1"))).thenReturn(tut);
        Mockito.when(tutorservice.findDistinctByTutorId(Mockito.matches("tut2"))).thenReturn(tut_noUid);
//        Mockito.when(tutorservice.saveOrUpdate(Mockito.any())).thenAnswer(
//                new Answer<Tutor>(){
//                    @Override
//                    public Tutor answer(InvocationOnMock invocation){
//                        return invocation.getArgument(0);
//                    }
//                }
//        );
    }

    @Test
    public void unbindTest() throws Exception{
        Map<String, String> map = new HashMap<>();
        map.put("realId", "s1");
        map.put("type", "1");
        String content = JSONObject.toJSONString(map);
        mockmvc.perform(MockMvcRequestBuilders.post("/api/admin/unbind")
                .contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(200));
                //.andReturn().getResponse().getContentAsString();
//        JSONObject obj = (JSONObject) new JSONParser(JSONParser.MODE_PERMISSIVE).parse(result);
//        assertEquals(200, obj.getAsNumber("code"));

        map.clear();
        map.put("realId", "s2");
        map.put("type", "1");
        content = JSONObject.toJSONString(map);

        mockmvc.perform(MockMvcRequestBuilders.post("/api/admin/unbind")
                .contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(400));
                //.andReturn().getResponse().getContentAsString();
//        obj = (JSONObject) new JSONParser(JSONParser.MODE_PERMISSIVE).parse(result);
//        assertEquals(400, obj.getAsNumber("code"));

        map.clear();
        map.put("realId", "tut1");
        map.put("type", "2");
        content = JSONObject.toJSONString(map);

        mockmvc.perform(MockMvcRequestBuilders.post("/api/admin/unbind")
                .contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(200));
                //.andReturn().getResponse().getContentAsString();
//        obj = (JSONObject) new JSONParser(JSONParser.MODE_PERMISSIVE).parse(result);
//        assertEquals(200, obj.getAsNumber("code"));

        map.clear();
        map.put("realId", "tut2");
        map.put("type", "2");
        content = JSONObject.toJSONString(map);

        mockmvc.perform(MockMvcRequestBuilders.post("/api/admin/unbind")
                .contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(400));
                //.andReturn().getResponse().getContentAsString();
//        obj = (JSONObject) new JSONParser(JSONParser.MODE_PERMISSIVE).parse(result);
//        assertEquals(400, obj.getAsNumber("code"));

        map.clear();
        map.put("realId", "tut2");
        map.put("type", "4");
        content = JSONObject.toJSONString(map);

        mockmvc.perform(MockMvcRequestBuilders.post("/api/admin/unbind")
                .contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(400))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":400}"));
                //.andReturn().getResponse().getContentAsString();
//        obj = (JSONObject) new JSONParser(JSONParser.MODE_PERMISSIVE).parse(result);
//        assertEquals(400, obj.getAsNumber("code"));

//        JSONObject obj = (JSONObject) new JSONParser(JSONParser.MODE_PERMISSIVE).parse(result);
//        System.out.println(obj.getAsNumber("code"));
    }
}