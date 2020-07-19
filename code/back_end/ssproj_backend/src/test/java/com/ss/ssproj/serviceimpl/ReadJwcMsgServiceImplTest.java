package com.ss.ssproj.serviceimpl;

import com.ss.ssproj.dao.ReadJwcMsgDao;
import com.ss.ssproj.entity.ReadJwcMsg;
import com.ss.ssproj.serviceimpl.ReadJwcMsgServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ReadJwcMsgServiceImplTest {
    @Autowired
    private ReadJwcMsgServiceImpl readjwcmsgserviceimpl;

    @MockBean
    private ReadJwcMsgDao readjwcmsgdao;

    @BeforeEach
    public void beforeEachTest(){
        ReadJwcMsg msg1 = new ReadJwcMsg(1, "s1", "tut1", 0, 0, 1);
        ReadJwcMsg msg2 = new ReadJwcMsg(2, "s2", "tut2", 1, 1, 4);

        Mockito.when(readjwcmsgdao.findDistinctByStudentidAndMsgid(Mockito.anyString(), Mockito.anyInt())).thenReturn(msg2);
        Mockito.when(readjwcmsgdao.findDistinctByTutoridAndMsgid(Mockito.anyString(), Mockito.anyInt())).thenReturn(msg1);
        Mockito.when(readjwcmsgdao.save(Mockito.any())).thenAnswer(
                new Answer<ReadJwcMsg>(){
                    @Override
                    public ReadJwcMsg answer(InvocationOnMock invocation){
                        return invocation.getArgument(0);
                    }
                }
        );
        Mockito.when(readjwcmsgdao.findAllByStudentidAndIfread(Mockito.anyString(), Mockito.anyInt())).thenAnswer(
                new Answer<List<ReadJwcMsg>>(){
                    @Override
                    public List<ReadJwcMsg> answer(InvocationOnMock invocation){
                        List<ReadJwcMsg> ret = new ArrayList<>();
                        ret.add(new ReadJwcMsg(1, "sId", "tutId", 0, 1, 1));
                        ret.add(new ReadJwcMsg(2, "sId", "tutId", 0, 1, 2));
                        return ret;
                    }
                }
        );
    }

    @Test
    public void findDistinctByStudentidAndMsgidTest(){
        ReadJwcMsg exp = new ReadJwcMsg(2, "s2", "tut2", 1, 1, 4);
        assertEquals(exp, readjwcmsgserviceimpl.findDistinctByStudentidAndMsgid("s2", 2));
    }

    @Test
    public void findDistinctByTutoridAndMsgidTest(){
        ReadJwcMsg exp = new ReadJwcMsg(1, "s1", "tut1", 0, 0, 1);
        assertEquals(exp, readjwcmsgserviceimpl.findDistinctByTutoridAndMsgid("tut1", 1));
    }

    @Test
    public void saveOrUpdateTest(){
        ReadJwcMsg newMsg = new ReadJwcMsg(3, "s3", "tut3", 0, 0, 5);
        assertEquals(newMsg, readjwcmsgserviceimpl.saveOrUpdate(newMsg));
    }

    @Test
    public void findAllByStudentidAndIfreadTest(){
        List<ReadJwcMsg> exp = new ArrayList<>();
        exp.add(new ReadJwcMsg(1, "sId", "tutId", 0, 1, 1));
        exp.add(new ReadJwcMsg(2, "sId", "tutId", 0, 1, 2));
        assertEquals(exp, readjwcmsgserviceimpl.findAllByStudentidAndIfread("sId", 0));
    }

}