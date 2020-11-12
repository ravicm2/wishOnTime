package com.wish.ontime.batch.utility;

import com.wish.ontime.model.User;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class EventUtility {
    public LocalDate getDate()
    {
        return LocalDate.now();
    }
    public Boolean filterUsers(LocalDate date , User user){

        return Integer.parseInt(user.getEventDate().split("/")[0]) == date.getDayOfMonth() &&
                        Integer.parseInt(user.getEventDate().split("/")[1]) == date.getMonthValue();

    }
}
