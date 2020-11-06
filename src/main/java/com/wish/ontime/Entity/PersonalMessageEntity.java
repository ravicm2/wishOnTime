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
        @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "PERSONALMSG_GENERATOR")
        @SequenceGenerator(name = "PERSONALMSG_GENERATOR",sequenceName = "PERSONALMSG_SEQ",allocationSize = 1)
        private Long id;
        private String message;
        private Date createdDate;
        private Date updatedDate;
}