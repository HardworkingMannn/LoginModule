package com.example.loginmodule.utils;


import com.example.loginmodule.constant.RabbitMQConst;
import com.example.loginmodule.pojo.EmailSendInfo;
import jakarta.validation.constraints.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
    @RabbitListener(queues ={RabbitMQConst.emailQueueName})
    public void sendEmail(EmailSendInfo info) throws MessagingException {
        String email=info.getEmail();
        String content=info.getContent();
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
