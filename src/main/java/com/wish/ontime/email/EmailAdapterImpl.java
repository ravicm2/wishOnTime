package com.wish.ontime.email;

import com.wish.ontime.email.api.EmailAdapter;
import com.wish.ontime.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EmailAdapterImpl implements EmailAdapter {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Override
    public void sendRemainderEmail(User user) {

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(user.getFromAddress());
            simpleMailMessage.setSentDate(new Date());
            simpleMailMessage.setText("Hi: don't forget to wish" + user.getReceiverName() + " regarding " + user.getEventType() + " on "+user.getEventDate());
            simpleMailMessage.setSubject("REMAINDER regarding" + user.getEventType());

            javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendEmail(User user) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getToAddress());
        simpleMailMessage.setSentDate(new Date());
        simpleMailMessage.setText("Hi "+user.getReceiverName()+"\n"+user.getPersonalMessage()+"\n"+"Regards,"+"\n"+user.getSenderName());
        simpleMailMessage.setSubject("WISH YOU HAPPY " + user.getEventType());
        simpleMailMessage.setReplyTo(user.getFromAddress());

        javaMailSender.send(simpleMailMessage);
    }
}
