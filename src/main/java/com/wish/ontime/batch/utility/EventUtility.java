package com.wish.ontime.batch.utility;

import com.wish.ontime.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EventUtility {

    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public boolean isUserHasEvent(LocalDate date, User user) {

        return Integer.parseInt(user.getEventDate().split("/")[0]) == date.getDayOfMonth() &&
                Integer.parseInt(user.getEventDate().split("/")[1]) == date.getMonthValue();

    }
}
