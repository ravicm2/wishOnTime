package com.wish.ontime.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "EventType")
@Getter
@Setter
@ToString(exclude = "id")
@AllArgsConstructor
@NoArgsConstructor
public class EventTypeEntity {

    @Id
    private Long id;
    private String eventType;
    private Date createdDate;
    private Date updatedDate;

    @OneToOne
    @JoinColumn(referencedColumnName = "id",nullable = true)
    List<PersonalMessageEntity> personalMessage;

}