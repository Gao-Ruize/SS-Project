package com.ss.ssproj.serviceimpl;

import com.ss.ssproj.dao.AdminDao;
import com.ss.ssproj.entity.Admin;
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
class AdminServiceImplTest {
    @Autowired
    private AdminServiceImpl adminserviceimpl;

    @MockBean
    private AdminDao admindao;

    @BeforeEach
    public void beforeEachTest(){
        Mockito.when(admindao.findDistinctByUserid(Mockito.anyString())).thenAnswer(
                new Answer<Admin>(){
                    @Override
                    public Admin answer(InvocationOnMock invocation){
                        return new Admin(0, "username", "password", "userid");
                    }
                }
        );
        Mockito.when(admindao.save(Mockito.any())).thenAnswer(
                new Answer<Admin>(){
                    @Override
                    public Admin answer(InvocationOnMock invocation){
                        return invocation.getArgument(0);
                    }
                }
        );
    }

    @Test
    void findDistinctByUserid() {
        Admin get = adminserviceimpl.findDistinctByUserid("userid");
        Admin exp = new Admin(0, "username", "password", "userid");
        assertEquals(exp, get);
    }

    @Test
    void save() {
        Admin toSave = new Admin(0, "username", "password", "userid");
        Admin get = adminserviceimpl.save(toSave);
        Admin exp = new Admin(0, "username", "password", "userid");
        assertEquals(exp, get);
    }
}