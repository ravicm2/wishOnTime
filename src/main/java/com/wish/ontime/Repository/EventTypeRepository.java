package com.wish.ontime.Repository;

import com.wish.ontime.Entity.EventTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventTypeRepository extends CrudRepository<EventTypeEntity,Long> {

}
