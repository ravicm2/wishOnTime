package com.wish.ontime.batch.chunk;

import com.wish.ontime.email.api.EmailAdapter;
import com.wish.ontime.model.User;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class EventWishWriter implements ItemWriter<User> {

    @Autowired
    private EmailAdapter emailAdapter;

    @Override
    public void write(List<? extends User> list) throws Exception {

        LocalDate today = LocalDate.now();

        //filtering users who has event
        list.stream().filter(user ->
                Integer.parseInt(user.getEventDate().split("/")[0]) == today.getDayOfMonth() &&
                        Integer.parseInt(user.getEventDate().split("/")[1]) == today.getMonthValue()
        ).forEach(emailAdapter::sendEmail);

    }
}