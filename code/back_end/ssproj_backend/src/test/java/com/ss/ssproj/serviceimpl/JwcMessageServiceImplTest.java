package com.ss.ssproj.serviceimpl;

import com.ss.ssproj.dao.JwcMessageDao;
import com.ss.ssproj.entity.JwcMessage;
import com.ss.ssproj.serviceimpl.JwcMessageServiceImpl;
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
class JwcMessageServiceImplTest {
    @Autowired
    private JwcMessageServiceImpl jwcmessageserviceimpl;

    @MockBean
    private JwcMessageDao jwcmessagedao;

    List<JwcMessage> msg_list = new ArrayList<>();

    @BeforeEach
    public void beforeEachTest(){
        JwcMessage msg1 = new JwcMessage(1, "time1", "title1", "content1", 1);
        JwcMessage msg2 = new JwcMessage(2, "time2", "title2", "content2", 2);
        JwcMessage msg3 = new JwcMessage(3, "time3", "title3", "content3", 3);
        this.msg_list.clear();
        this.msg_list.add(msg1);
        this.msg_list.add(msg2);
        this.msg_list.add(msg3);

        Mockito.when(jwcmessagedao.findAll()).thenReturn(msg_list);
        Mockito.when(jwcmessagedao.findByJwcId(Mockito.anyInt())).thenReturn(msg1);
    }

    @Test
    public void findByIdTest(){
        JwcMessage exp = new JwcMessage(1, "time1", "title1", "content1", 1);
        assertEquals(exp, jwcmessageserviceimpl.findById(1));
    }

    @Test
    public void findAllTest(){
        assertEquals(this.msg_list, jwcmessageserviceimpl.findAll());
    }

}