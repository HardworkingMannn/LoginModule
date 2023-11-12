package com.example.loginmodule.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
@Slf4j
public class EmailSender {//发送邮件的工具类
    @Value("${sender.email}")
    private String username;
    @Autowired
    private Session session;
    public void sendEmail(String email,String content) throws MessagingException {
        //	创建Session会话
//	创建邮件对象
        log.info("发送邮件给{}",email);
        MimeMessage message = new MimeMessage(session);
        message.setSubject("AIPaint验证码发送");
        message.setText(content);
        message.setFrom(new InternetAddress(username));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email));
        message.setRecipient(Message.RecipientType.CC, new InternetAddress(username));
//	发送
        Transport.send(message);
    }
}
