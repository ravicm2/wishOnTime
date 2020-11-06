package com.wish.ontime.Entity;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "USER_SEQ")
    private Long id;

    @Column(nullable = false)
    @Email(message = "invalid email")
    private String fromAddress;

    @Column(nullable = false)
    @Email(message = "invalid email")
    private String toAddress;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String senderName;

    @Column(nullable = false)
    private boolean remainder;

    @Column(nullable = false)
    private boolean wished;

    private Date createdDate;

    private Date updatedDate;

    @Column(nullable = false)
    private Date eventDate;

    @OneToMany
    @JoinColumn(referencedColumnName = "id", nullable = false,name = "event_id",unique = true)
    Set<EventTypeEntity> eventTypes;

}