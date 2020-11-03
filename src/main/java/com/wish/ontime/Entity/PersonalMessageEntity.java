package com.wish.ontime.Entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
        private Long id;
        private String message;
        private Date createdDate;
        private Date updatedDate;
}