package com.wish.ontime.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "User")
@Getter
@Setter
@ToString(exclude = "id")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    private Long id;

    private String fromAddress;

    private String toAddress;

    private String fromName;

    private String toName;

    private Boolean remainder;

    private Boolean wished = false;

    private Date createDate;

    private Date updateDate;

    private Date eventDate;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    Set<EventTypeEntity> eventType;

}