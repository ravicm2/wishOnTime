package com.wish.ontime.batch_remainder.chunk;

import com.wish.ontime.Entity.UserEntity;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventWriter implements ItemWriter<UserEntity> {

    @Override
    public void write(List<? extends UserEntity> list) throws Exception {

    }
}
