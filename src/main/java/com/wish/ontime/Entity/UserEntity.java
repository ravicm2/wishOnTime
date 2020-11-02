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
    private Long id;

    @Column(nullable = false)
    @Email
    private String fromAddress;

    @Column(nullable = false)
    @Email
    private String toAddress;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String senderName;

    @Column(nullable = false)
    private boolean remainder;

    @Column(nullable = false)
    private boolean wished = false;

    private Date createdDate;

    private Date updatedDate;

    @Column(nullable = false)
    private Date eventDate;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    Set<EventTypeEntity> eventTypes;

}