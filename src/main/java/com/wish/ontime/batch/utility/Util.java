package com.wish.ontime.batch.utility;

import com.wish.ontime.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Component
public class Util {
    public LocalDate getDate()
    {
        return LocalDate.now();
    }
    public Stream<? extends User> filterUsers(LocalDate date, List<? extends User> list){

        return list.stream().filter(user ->
                Integer.parseInt(user.getEventDate().split("/")[0]) == date.getDayOfMonth() &&
                        Integer.parseInt(user.getEventDate().split("/")[1]) == date.getMonthValue());

    }
}
