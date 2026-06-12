package com.interview.fraudalert.service;
import com.interview.fraudalert.event.*; import org.slf4j.*; import org.springframework.beans.factory.annotation.*; import org.springframework.kafka.annotation.KafkaListener; import org.springframework.kafka.core.KafkaTemplate; import org.springframework.stereotype.Service; import java.time.Instant;
@Service
public class FraudDetectionConsumer {
  private static final Logger log=LoggerFactory.getLogger(FraudDetectionConsumer.class); private final FraudRuleEngine ruleEngine; private final KafkaTemplate<String, FraudEvaluatedEvent> kafkaTemplate; private final String outputTopic;
  public FraudDetectionConsumer(FraudRuleEngine ruleEngine, KafkaTemplate<String, FraudEvaluatedEvent> kafkaTemplate, @Value("${app.kafka.topics.fraud-evaluated}") String outputTopic){ this.ruleEngine=ruleEngine; this.kafkaTemplate=kafkaTemplate; this.outputTopic=outputTopic; }
  @KafkaListener(topics="${app.kafka.topics.transaction-received}", groupId="fraud-detection")
  public void onTransactionReceived(TransactionReceivedEvent event){
    var eval=ruleEngine.evaluate(event); FraudEvaluatedEvent out=new FraudEvaluatedEvent(event.transactionId(), event.accountId(), event.amount(), event.merchant(), event.location(), eval.riskLevel(), eval.reason(), Instant.now());
    kafkaTemplate.send(outputTopic, out.transactionId().toString(), out).whenComplete((r,e)->{ if(e!=null) log.error("Failed to publish fraud evaluation {}", out.transactionId(), e); });
  }
}
