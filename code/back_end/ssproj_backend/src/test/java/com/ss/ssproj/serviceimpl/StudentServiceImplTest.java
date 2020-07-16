package com.ss.ssproj.serviceimpl;

import com.ss.ssproj.dao.StudentDao;
import com.ss.ssproj.entity.Student;
import com.ss.ssproj.serviceimpl.StudentServiceImpl;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class StudentServiceImplTest {
    @Autowired
    private StudentServiceImpl studentserviceimpl;

    @MockBean
    private StudentDao studentdao;

    @BeforeEach
    public void beforeEachTest(){
        Student st1 = new Student(1, "s1", "abc", "name1");
        Student st2 = new Student(2, "s2", "cde", "name2");

        Mockito.when(studentdao.findDistinctByUid(Mockito.anyString())).thenReturn(st1);
        Mockito.when(studentdao.findDistinctByStudentId(Mockito.anyString())).thenReturn(st2);
        Mockito.when(studentdao.saveOrUpdate(Mockito.any())).thenAnswer(
                new Answer<Student>(){
                    @Override
                    public Student answer(InvocationOnMock invocation){
                        return invocation.getArgument(0);
                    }
                }
        );
    }

    @Test
    public void saveOrUpdateTest(){
        Student newSt = new Student(3, "s3", "efg", "name3");
        assertEquals(newSt, studentserviceimpl.saveOrUpdate(newSt));
    }

    @Test
    public void findDistinctByUidTest(){
        Student exp = new Student(1, "s1", "abc", "name1");
        assertEquals(exp, studentserviceimpl.findDistinctByUid("abc"));
    }

    @Test
    public void findDistinctByStudentIdTest(){
        Student exp = new Student(2, "s2", "cde", "name2");
        assertEquals(exp, studentserviceimpl.findDistinctByStudentId("s2"));
    }
}