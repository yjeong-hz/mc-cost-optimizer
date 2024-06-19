package com.mcmp.slack_demo.mail;

import com.mcmp.slack_demo.common.model.CommonResultModel;
import com.mcmp.slack_demo.mail.dao.MailingDao;
import com.mcmp.slack_demo.mail.model.MailMessage;
import com.mcmp.slack_demo.mail.model.MailingInfoModel;
import com.mcmp.slack_demo.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/cost")
public class MailController {
    @Autowired
    private MailService mailService;

    @Autowired
    private MailingDao mailingDao;

    @PostMapping("/sendAlertMail")
    public ResponseEntity<CommonResultModel> sendAlertMail(@RequestBody MailMessage mailMessage){
        CommonResultModel result = new CommonResultModel();
        try{
            ClassPathResource resource = new ClassPathResource("static/images/testmemo.txt");
            mailService.sendEmail(mailMessage, "alert", resource);
        } catch (Exception e){
            result.setError(400, "Send AlertEmail Fail");
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("testGet")
    public void testGet(){
        System.out.println(mailingDao.getMailingInfo());
    }

    @PostMapping("insertMailInfo")
    public ResponseEntity<CommonResultModel> insertMailingInfo(@RequestBody MailingInfoModel model){
        CommonResultModel result = new CommonResultModel();
        try{
            mailingDao.insertMailingInfo(model);
        } catch (Exception e){
            e.printStackTrace();
            result.setError(400, "Insert AlertEmail Info Fail");
        }
        return ResponseEntity.ok(result);
    }

}
