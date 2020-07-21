//向老师和学生提供所有的教务处信息
//并更新阅读情况
package com.ss.ssproj.controller;

import com.ss.ssproj.entity.JwcMessage;
import com.ss.ssproj.entity.ReadJwcMsg;
import com.ss.ssproj.service.JwcMessageService;
import com.ss.ssproj.service.ReadJwcMsgService;
import com.ss.ssproj.utils.MsgForm;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JwcMsgController {
    @Autowired
    JwcMessageService jwcMessageService;
    @Autowired
    ReadJwcMsgService readJwcMsgService;

    //获取所有的教务处通知
    @CrossOrigin
    @GetMapping(value = "api/user/jwcmsgs")
    @ResponseBody
    public List<JwcMessage> jwcmsgs() {
        return jwcMessageService.findAll();
    }

    //根据id与type获得当前用户每条jwc信息的状态
    @CrossOrigin
    @GetMapping(value = "api/user/typejwcmsg/{type}/{userId}")
    @ResponseBody
    public List<JwcMessage> typejwcmsgs(@PathVariable("type") String type,
                                        @PathVariable("userId") String usetId) {
        List<JwcMessage> jwcMessages = jwcMessageService.findAll();
        if(type.equals("T")) {
            for(JwcMessage item : jwcMessages) {
                int msgId = item.getId();
                ReadJwcMsg readJwcMsg = readJwcMsgService.findDistinctByTutoridAndMsgid(usetId, msgId);
                item.setIfRead(readJwcMsg.getIfread());
            }
        } else if(type.equals("S")) {
            for(JwcMessage item : jwcMessages) {
                int msgId = item.getId();
                ReadJwcMsg readJwcMsg = readJwcMsgService.findDistinctByStudentidAndMsgid(usetId, msgId);
                item.setIfRead(readJwcMsg.getIfread());
            }
        }
        List<JwcMessage> sortRet = new ArrayList<>();
        for(int i = 0; i < jwcMessages.size(); ++ i) {
            JwcMessage item = jwcMessages.get(i);
            if(item.getIfRead() == 0) {
                sortRet.add(item);
                jwcMessages.remove(i);
                i --;
            }
        }
        sortRet.addAll(jwcMessages);
        return sortRet;
    }
}
