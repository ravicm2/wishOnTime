package com.wish.ontime.email.api;

import com.wish.ontime.model.User;

public interface EmailAdapter {

    public void sendRemainderEmail(User user);

    public void sendEmail(User user);
}
