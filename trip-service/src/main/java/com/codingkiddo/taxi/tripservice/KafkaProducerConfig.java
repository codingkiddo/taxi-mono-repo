package com.codingkiddo.taxi.tripservice;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import java.util.*;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProducerConfig {
  @Bean
  public ProducerFactory<String, Object> producerFactory(TripKafkaProps props) {
    Map<String, Object> cfg = new HashMap<>();
    cfg.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, props.bootstrap());
    cfg.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    cfg.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
    cfg.put("schema.registry.url", props.schemaRegistry());
    return new DefaultKafkaProducerFactory<>(cfg);
  }

  @Bean
  public KafkaTemplate<String, Object> kt(ProducerFactory<String, Object> pf) {
    return new KafkaTemplate<>(pf);
  }
}
