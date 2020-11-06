package com.wish.ontime.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PersonalMessage")
@Setter
@Getter
@ToString(exclude = "id")
@AllArgsConstructor
@NoArgsConstructor
public class PersonalMessageEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "PERSONALMSG_SEQ")
        private Long id;
        private String message;
        private Date createdDate;
        private Date updatedDate;
}