package com.zim.kafka.poc.stream;

import com.zim.kafka.poc.connect.USERS;
import io.confluent.developer.EnrichedUser;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@EnableKafkaStreams
public class KafkaStreamsConfig {

    @Value("${sourceTopic.name}")
    private String SOURCE_TOPIC;

    @Value("${destinationTopic.name}")
    private String DESTINATION_TOPIC;

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kStreamsConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "StreamsPOC3");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);
        props.put("schema.registry.url", "http://localhost:8081");
        return new KafkaStreamsConfiguration(props);
    }


    @Bean
    public KStream<String, USERS> kStream(StreamsBuilder kStreamBuilder) {
        KStream<String, USERS> stream = kStreamBuilder.stream(SOURCE_TOPIC);
        stream.map((key, user) -> new KeyValue<String, EnrichedUser>(key, new EnrichedUser(user.getNAME(),
                                                                                            user.getAGE(),
                                                                                        "Developer")))
                .to(DESTINATION_TOPIC);

        System.out.println(stream);

        return stream;
    }

}