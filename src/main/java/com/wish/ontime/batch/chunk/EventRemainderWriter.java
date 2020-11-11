package com.wish.ontime.batch.chunk;

import com.wish.ontime.batch.utility.EventUtility;
import com.wish.ontime.email.api.EmailAdapter;
import com.wish.ontime.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class EventRemainderWriter implements ItemWriter<User> {

    private static final Logger LOG = LoggerFactory.getLogger(EventRemainderWriter.class);
    @Autowired
    private EmailAdapter emailAdapter;

    @Autowired
    private EventUtility eventUtility;

    @Override
    public void write(List<? extends User> list) throws Exception {

        LOG.info("Remainder writer executing");

        //uncomment after fixing time bug.
//      LocalDate tomorrow = LocalDate.now().plusDays(1);

        //as of now implementation to run at 12am.
        LocalDate today = eventUtility.getDate();

        list.stream().filter(user -> eventUtility.filterUsers(today,user) && Boolean.TRUE.toString().equals(user.getRemainder()))
                .forEach(emailAdapter::sendRemainderEmail);

        //filtering users who opted for remainder and sending mail.

//         list.stream().filter(user ->
//                Integer.parseInt(user.getEventDate().split("/")[0]) == today.getDayOfMonth() &&
//                        Integer.parseInt(user.getEventDate().split("/")[1]) == today.getMonthValue() &&
//                        Boolean.TRUE.toString().equals(user.getRemainder())
//        ).forEach(emailAdapter::sendRemainderEmail);

    }
}
