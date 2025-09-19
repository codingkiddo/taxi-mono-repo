package com.codingkiddo.taxi.paymentservice;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import java.util.*;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

@Configuration
public class KafkaConfig {
  @Bean
  public ConsumerFactory<String, Object> consumerFactory(PaymentKafkaProps props) {
    Map<String, Object> cfg = new HashMap<>();
    cfg.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, props.bootstrap());
    cfg.put(ConsumerConfig.GROUP_ID_CONFIG, "payment-consumers");
    cfg.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    cfg.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
    cfg.put("schema.registry.url", props.schemaRegistry());
    return new DefaultKafkaConsumerFactory<>(cfg);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory(
      ConsumerFactory<String, Object> cf) {
    var f = new ConcurrentKafkaListenerContainerFactory<String, Object>();
    f.setConsumerFactory(cf);
    return f;
  }

  @Bean
  public ProducerFactory<String, Object> producerFactory(PaymentKafkaProps props) {
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
