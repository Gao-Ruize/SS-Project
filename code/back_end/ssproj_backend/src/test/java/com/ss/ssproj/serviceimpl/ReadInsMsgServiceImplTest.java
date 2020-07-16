package com.ss.ssproj.serviceimpl;

import com.ss.ssproj.dao.ReadInsMsgDao;
import com.ss.ssproj.entity.ReadInsMsg;
import com.ss.ssproj.serviceimpl.ReadInsMsgServiceImpl;
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
class ReadInsMsgServiceImplTest {
    @Autowired
    private ReadInsMsgServiceImpl readinsmsgserviceimpl;

    @MockBean
    private ReadInsMsgDao readinsmsgdao;

    List<ReadInsMsg> msg_list = new ArrayList<>();

    @BeforeEach
    public void beforeEachTest(){
        ReadInsMsg msg1 = new ReadInsMsg(1, "s1", 0, 1);
        ReadInsMsg msg2 = new ReadInsMsg(2, "s2", 1, 2);
        ReadInsMsg msg3 = new ReadInsMsg(3, "s2", 0, 1);
        ReadInsMsg msg4 = new ReadInsMsg(4, "s3", 1, 2);
        this.msg_list.clear();
        this.msg_list.add(msg1);
        this.msg_list.add(msg2);
        this.msg_list.add(msg3);
        this.msg_list.add(msg4);

        Mockito.when(readinsmsgdao.findDistinctByStudentidAndMsgid(Mockito.anyString(), Mockito.anyInt())).thenReturn(msg1);
        Mockito.when(readinsmsgdao.save(Mockito.any())).thenAnswer(
                new Answer<ReadInsMsg>(){
                    @Override
                    public ReadInsMsg answer(InvocationOnMock invocation){
                        return invocation.getArgument(0);
                    }
                }
        );
        Mockito.when(readinsmsgdao.findAllByStudentid(Mockito.anyString())).thenAnswer(
                new Answer<List<ReadInsMsg>>(){
                    @Override
                    public List<ReadInsMsg> answer(InvocationOnMock invocation){
                        List<ReadInsMsg> result = new ArrayList<>();
                        result.add(msg2);
                        result.add(msg3);
                        return result;
                    }
                }
        );
        Mockito.when(readinsmsgdao.findAllByMsgid(Mockito.anyInt())).thenAnswer(
                new Answer<List<ReadInsMsg>>(){
                    @Override
                    public List<ReadInsMsg> answer(InvocationOnMock invocation){
                        List<ReadInsMsg> result = new ArrayList<>();
                        result.add(msg1);
                        result.add(msg3);
                        return result;
                    }
                }
        );
    }

    @Test
    public void findDistinctByStudentidndMsgidTest(){
        ReadInsMsg exp = new ReadInsMsg(1, "s1", 0, 1);
        assertEquals(exp, readinsmsgserviceimpl.findDistinctByStudentidAndMsgid("s1", 1));
    }

    @Test
    public void saveTest(){
        ReadInsMsg newMsg = new ReadInsMsg(5, "s4", 0, 5);
        ReadInsMsg returnMsg = readinsmsgserviceimpl.save(newMsg);
        assertEquals(newMsg, returnMsg);
    }

    @Test
    public void findAllByStudentidTest(){
        List<ReadInsMsg> exp = new ArrayList<>();
        ReadInsMsg msg1 = new ReadInsMsg(2, "s2", 1, 2);
        ReadInsMsg msg2 = new ReadInsMsg(3, "s2", 0, 1);
        exp.add(msg1);
        exp.add(msg2);
        assertEquals(exp, readinsmsgserviceimpl.findAllByStudentid("s2"));
    }

    @Test
    public void findAllByMsgidTest(){
        List<ReadInsMsg> exp = new ArrayList<>();
        ReadInsMsg msg1 = new ReadInsMsg(1, "s1", 0, 1);
        ReadInsMsg msg2 = new ReadInsMsg(3, "s2", 0, 1);
        exp.add(msg1);
        exp.add(msg2);
        assertEquals(exp, readinsmsgserviceimpl.findAllByMsgid(1));
    }
}