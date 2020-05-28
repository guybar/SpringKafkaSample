package com.zim.kafka.poc.consumer;

import io.confluent.developer.EnrichedUser;
import io.confluent.developer.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UsersStream {

    @KafkaListener(topics = {"enrichedUsers"})
    public void process(ConsumerRecord<String, EnrichedUser> user) {
        System.out.println(user.toString());
    }
}
