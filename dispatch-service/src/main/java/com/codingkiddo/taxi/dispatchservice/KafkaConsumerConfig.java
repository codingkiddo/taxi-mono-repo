package com.codingkiddo.taxi.dispatchservice;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import java.util.*;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
public class KafkaConsumerConfig {
  @Bean
  public ConsumerFactory<String, Object> consumerFactory(DispatchKafkaProps props) {
    Map<String, Object> cfg = new HashMap<>();
    cfg.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, props.bootstrap());
    cfg.put(ConsumerConfig.GROUP_ID_CONFIG, "dispatch-consumers");
    cfg.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    cfg.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
    cfg.put("schema.registry.url", props.schemaRegistry());
    cfg.put("specific.avro.reader", false);
    return new DefaultKafkaConsumerFactory<>(cfg);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory(
      ConsumerFactory<String, Object> cf) {
    var f = new ConcurrentKafkaListenerContainerFactory<String, Object>();
    f.setConsumerFactory(cf);
    return f;
  }
}
