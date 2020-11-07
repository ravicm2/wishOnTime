package com.wish.ontime.model;

import lombok.*;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private String fromAddress;
    private String toAddress;
    private String senderName;
    private String receiverName;
    private String remainder;
    private String eventType;
    private String eventDate;
    private String personalMessage;
}
