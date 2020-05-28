package zim.kafka.poc.producer;

import com.zim.kafka.poc.connect.USERS;
import io.confluent.developer.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    @Value("${topic.name}")
    private String TOPIC;

    private final KafkaTemplate<String, USERS> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate<String, USERS> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    void sendMessage(USERS user) {
        this.kafkaTemplate.send(this.TOPIC, user.getNAME(), user);
        }
}
