package com.ss.ssproj.serviceimpl;

import com.ss.ssproj.dao.InstructDao;
import com.ss.ssproj.entity.Instruct;
import com.ss.ssproj.serviceimpl.InstructServiceImpl;
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
class InstructServiceImplTest {
    @Autowired
    private InstructServiceImpl instructserviceimpl;

    @MockBean
    private InstructDao instructdao;

    private List<Instruct> i_list = new ArrayList<>();

    @BeforeEach
    public void beforeEachTest(){
        Instruct ins1 = new Instruct(1, "s1", "tut1");
        Instruct ins2 = new Instruct(2, "s2", "tut2");
        Instruct ins3 = new Instruct(3, "s3", "tut1");
        this.i_list.clear();
        this.i_list.add(ins1);
        this.i_list.add(ins2);
        this.i_list.add(ins3);

        Mockito.when(instructdao.findAll()).thenReturn(i_list);
        Mockito.when(instructdao.save(Mockito.any())).thenAnswer(
                new Answer<Instruct>(){
                    @Override
                    public Instruct answer(InvocationOnMock invocation){
                        return invocation.getArgument(0);
                    }
                }
        );
        Mockito.when(instructdao.findAllByTutorid(Mockito.anyString())).thenAnswer(
                new Answer<List<Instruct>>(){
                    @Override
                    public List<Instruct> answer(InvocationOnMock invocation){
                        List<Instruct> result = new ArrayList<Instruct>();
                        result.add(ins1);
                        result.add(ins3);
                        return result;
                    }
                }
        );
        Mockito.when(instructdao.findDistinctByStudentidAndTutorid(Mockito.anyString(), Mockito.anyString())).thenAnswer(
                new Answer<Instruct>(){
                    @Override
                    public Instruct answer(InvocationOnMock invocation){
                        Instruct ret = new Instruct(1, "sId", "tutId");
                        return ret;
                    }
                }
        );
    }

    @Test
    public void findAllTest(){
        assertEquals(this.i_list, instructserviceimpl.findAll());
    }

    @Test
    public void saveOrUpdateTest(){
        Instruct newIns = new Instruct(4, "s4", "tut4");
        Instruct returnIns = instructserviceimpl.saveOrUpdate(newIns);
        assertEquals(newIns, returnIns);
    }

    @Test
    public void findAllByTutoridTest(){
        List<Instruct> exp = new ArrayList<Instruct>();
        Instruct ins1 = new Instruct(1, "s1", "tut1");
        Instruct ins2 = new Instruct(3, "s3", "tut1");
        exp.add(ins1);
        exp.add(ins2);
        assertEquals(exp, instructserviceimpl.findAllByTutorid("tut1"));
    }

    @Test
    public void findDistinctByStudentidAndTutoridTest(){
        Instruct exp = new Instruct(1, "sId", "tutId");
        assertEquals(exp, instructserviceimpl.findDistinctByStudentidAndTutorid("sId", "tutId"));
    }
}