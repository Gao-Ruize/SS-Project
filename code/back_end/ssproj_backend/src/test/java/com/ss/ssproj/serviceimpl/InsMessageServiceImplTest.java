package com.ss.ssproj.serviceimpl;

import com.ss.ssproj.dao.InsMessageDao;
import com.ss.ssproj.entity.InsMessage;
import com.ss.ssproj.serviceimpl.InsMessageServiceImpl;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class InsMessageServiceImplTest {
    @Autowired
    private InsMessageServiceImpl insmessageserviceimpl;

    @MockBean
    private InsMessageDao insmessagedao;

    List<InsMessage> msg_list = new ArrayList<>();

    @BeforeEach
    public void beforeEachTest(){
        InsMessage msg1 = new InsMessage(1, "Tutor1", "Title1", "Content1", "ReleaseTime1");
        InsMessage msg2 = new InsMessage(2, "Tutor2", "Title2", "Content2", "ReleaseTime2");
        InsMessage msg3 = new InsMessage(3, "Tutor1", "Title3", "Content3", "ReleaseTime3");
        this.msg_list.clear();
        this.msg_list.add(msg1);
        this.msg_list.add(msg2);
        this.msg_list.add(msg3);

        Mockito.when(insmessagedao.findDistinctById(Mockito.anyInt())).thenReturn(msg1);
        Mockito.when(insmessagedao.save(Mockito.any())).thenAnswer(
                new Answer<InsMessage>(){
                    @Override
                    public InsMessage answer(InvocationOnMock invocation){
                        return invocation.getArgument(0);
                    }
                }
        );
        Mockito.when(insmessagedao.findAllByTutorid(Mockito.anyString())).thenAnswer(
                new Answer<List<InsMessage>>(){
                    @Override
                    public List<InsMessage> answer(InvocationOnMock invocation){
                        List<InsMessage> result = new ArrayList<>();
                        result.add(msg1);
                        result.add(msg3);
                        return result;
                    }
                }
        );
    }

    @Test
    public void findDistinctByIdTest(){
        InsMessage exp = new InsMessage(1, "Tutor1", "Title1", "Content1", "ReleaseTime1");
        assertEquals(exp, insmessageserviceimpl.findDistinctById(1));
    }

    @Test
    public void saveOrUpdateTest(){
        InsMessage newMsg = new InsMessage(2, "Tutor2", "newTitle", "newContent", "newReleaseTime");
        InsMessage returnMsg = insmessageserviceimpl.saveOrUpdate(newMsg);
        assertEquals(newMsg, returnMsg);
    }

    @Test
    public void findAllByTutorid(){
        List<InsMessage> exp = new ArrayList<>();
        InsMessage msg1 = new InsMessage(1, "Tutor1", "Title1", "Content1", "ReleaseTime1");
        InsMessage msg2 = new InsMessage(3, "Tutor1", "Title3", "Content3", "ReleaseTime3");
        exp.add(msg1);
        exp.add(msg2);
        assertEquals(exp, insmessageserviceimpl.findAllByTutorid("Tutor1"));
    }
}