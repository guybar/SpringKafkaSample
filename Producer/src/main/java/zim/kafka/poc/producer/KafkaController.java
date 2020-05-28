package zim.kafka.poc.producer;

import com.zim.kafka.poc.connect.USERS;
import io.confluent.developer.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/user")
public class KafkaController {

    private final Producer producer;

    @Autowired
    KafkaController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("name") Integer id, @RequestParam("name") String name, @RequestParam("age") Integer age) {
        this.producer.sendMessage(new USERS(BigDecimal.valueOf(id), name, BigDecimal.valueOf(age)));
    }
}
