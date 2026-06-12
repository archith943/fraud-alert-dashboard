package com.interview.fraudalert.config;
import org.apache.kafka.clients.admin.NewTopic; import org.springframework.beans.factory.annotation.Value; import org.springframework.context.annotation.*; import org.springframework.kafka.config.TopicBuilder;
@Configuration
public class KafkaTopicConfig {
  @Bean NewTopic transactionReceivedTopic(@Value("${app.kafka.topics.transaction-received}") String topic){ return TopicBuilder.name(topic).partitions(3).replicas(1).build(); }
  @Bean NewTopic fraudEvaluatedTopic(@Value("${app.kafka.topics.fraud-evaluated}") String topic){ return TopicBuilder.name(topic).partitions(3).replicas(1).build(); }
}
