package com.codingkiddo.taxi.driverservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.kafka")
public class DriverKafkaProps {
  private String bootstrap;
  private String schemaRegistry;

  public String bootstrap() {
    return bootstrap;
  }

  public void setBootstrap(String s) {
    this.bootstrap = s;
  }

  public String schemaRegistry() {
    return schemaRegistry;
  }

  public void setSchemaRegistry(String s) {
    this.schemaRegistry = s;
  }
}
