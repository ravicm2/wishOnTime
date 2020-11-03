package com.wish.ontime.Repository;

import com.wish.ontime.Entity.PersonalMessageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalMessageRepository extends CrudRepository<PersonalMessageEntity,Long> {
}
