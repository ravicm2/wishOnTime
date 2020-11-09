package com.wish.ontime.email;

import com.wish.ontime.email.api.EmailAdapter;
import com.wish.ontime.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EmailAdapterImpl implements EmailAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(EmailAdapterImpl.class);

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Override
    public void sendRemainderEmail(User user) {

        LOG.info("sending remainder mail");

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getFromAddress());
        simpleMailMessage.setSentDate(new Date());
        simpleMailMessage.setText("Hi "+user.getSenderName() +",\n \n"+"don't forget to wish " + user.getReceiverName() + " regarding " + user.getEventType() + " on " + user.getEventDate()
        +"\n"+"\n"+"Regards,"+"\n"+"REMAINDER SERVICES.")
        ;
        simpleMailMessage.setSubject("REMAINDER REGARDING EVENT " + user.getEventType());

        javaMailSender.send(simpleMailMessage);

        LOG.info("sent remainder mail");
    }

    @Override
    public void sendEmail(User user) {

        LOG.info("sending wish mail");

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getToAddress());
        simpleMailMessage.setSentDate(new Date());
        simpleMailMessage.setText("Hi " + user.getReceiverName() + ",\n\n" + user.getPersonalMessage() + "\n\n" + "Regards," + "\n" + user.getSenderName());
        simpleMailMessage.setSubject("WISH YOU HAPPY " + user.getEventType());
        simpleMailMessage.setReplyTo(user.getFromAddress());

        javaMailSender.send(simpleMailMessage);

        LOG.info("sent wish mail");
    }
}
