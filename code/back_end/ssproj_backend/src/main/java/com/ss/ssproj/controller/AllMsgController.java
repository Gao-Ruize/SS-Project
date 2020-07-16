package com.ss.ssproj.controller;

import com.ss.ssproj.entity.InsMessage;
import com.ss.ssproj.entity.JwcMessage;
import com.ss.ssproj.entity.Tutor;
import com.ss.ssproj.service.InsMessageService;
import com.ss.ssproj.service.JwcMessageService;
import com.ss.ssproj.service.TutorService;
import com.ss.ssproj.utils.MsgForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AllMsgController {
    @Autowired
    InsMessageService insMessageService;

    @Autowired
    JwcMessageService jwcMessageService;

    @Autowired
    TutorService tutorService;

    @CrossOrigin
    @ResponseBody
    @GetMapping(value = "api/user/msgdetail/{msgid}/{type}")
    public MsgForm getMsgDetail(@PathVariable("msgid")int msgid,@PathVariable("type")String type) {
        MsgForm msgForm = new MsgForm();
        if(type.equals("jwc")) {
            JwcMessage jwcMessage = jwcMessageService.findById(msgid);
            if(jwcMessage != null) {
                msgForm.setContent(jwcMessage.getContent());
                msgForm.setTitle(jwcMessage.getTitle());
                msgForm.setTime(jwcMessage.getReleasetime());
                msgForm.setSenderName("jwc");
                return msgForm;
            }
        } else if(type.equals("tutor")) {
            InsMessage insMessage = insMessageService.findDistinctById(msgid);
            if(insMessage != null) {
                msgForm.setTitle(insMessage.getTitle());
                msgForm.setContent(insMessage.getContent());
                msgForm.setTime(insMessage.getReleasetime());
                Tutor tutor = tutorService.findDistinctByTutorId(insMessage.getTutorid());
                msgForm.setSenderName(tutor.getTutorname());
                return msgForm;
            }
        }
        return msgForm;
    }
}
