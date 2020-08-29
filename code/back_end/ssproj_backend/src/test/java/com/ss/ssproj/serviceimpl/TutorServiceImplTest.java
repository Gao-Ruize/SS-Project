package com.ss.ssproj.serviceimpl;

import com.ss.ssproj.dao.TutorDao;
import com.ss.ssproj.entity.Tutor;
import com.ss.ssproj.serviceimpl.TutorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TutorServiceImplTest {
    @Autowired
    private TutorServiceImpl tutorserviceimpl;

    @MockBean
    private TutorDao tutordao;

    List<Tutor> tut_list = new ArrayList<>();

    @BeforeEach
    public void beforeEachTest(){
        Tutor tut1 = new Tutor(1, "tut1", "uid1", "name1");
        Tutor tut2 = new Tutor(2, "tut2", "uid2", "name2");
        Tutor tut3 = new Tutor(3, "tut3", "uid3", "name3");
        this.tut_list.clear();
        this.tut_list.add(tut1);
        this.tut_list.add(tut2);
        this.tut_list.add(tut3);

        Mockito.when(tutordao.findAll()).thenReturn(tut_list);
        Mockito.when(tutordao.findDistinctByUid(Mockito.anyString())).thenReturn(tut1);
        Mockito.when(tutordao.findDistinctByTutorId(Mockito.anyString())).thenReturn(tut2);
    }

    @Test
    public void findAllTest(){
        assertEquals(this.tut_list, tutorserviceimpl.findAll());
    }

    @Test
    public void saveOrUpdateTest(){
        tutorserviceimpl.saveOrUpdate(null);
    }

    @Test
    public void findDistinctByUidTest(){
        Tutor exp = new Tutor(1, "tut1", "uid1", "name1");
        assertEquals(exp, tutorserviceimpl.findDistinctByUid("uid1"));
    }

    @Test
    public void findDistinctByTutorIdTest(){
        Tutor exp = new Tutor(2, "tut2", "uid2", "name2");
        assertEquals(exp, tutorserviceimpl.findDistinctByTutorId("tut2"));
    }
}