package com.wish.ontime.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String fromAddress;
    private String toAddress;
    private String senderName;
    private String receiverName;
    private boolean remainder;
    private String eventType;
    private Date eventDate;
    private String personalMessage;
}
