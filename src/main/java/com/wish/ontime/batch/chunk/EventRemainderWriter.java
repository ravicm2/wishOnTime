package com.wish.ontime.batch.chunk;

import com.wish.ontime.email.api.EmailAdapter;
import com.wish.ontime.model.User;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class EventRemainderWriter implements ItemWriter<User> {

    @Autowired
    private EmailAdapter emailAdapter;

    @Override
    public void write(List<? extends User> list) throws Exception {

        //uncomment after fixing time bug.
//        LocalDate tomorrow = LocalDate.now().plusDays(1);

        //as of now implementation to run at 12am.
        LocalDate today = LocalDate.now();

        //filtering users who opted for remainder and sending mail.
         list.stream().filter(user ->
                Integer.parseInt(user.getEventDate().split("/")[0]) == today.getDayOfMonth() &&
                        Integer.parseInt(user.getEventDate().split("/")[1]) == today.getMonthValue() &&
                        Boolean.getBoolean(user.getRemainder())
        ).forEach(emailAdapter::sendRemainderEmail);

    }
}
