package com.interview.fraudalert.service;
import com.interview.fraudalert.dto.*; import com.interview.fraudalert.event.TransactionReceivedEvent; import com.interview.fraudalert.exception.KafkaPublishException; import org.springframework.beans.factory.annotation.*; import org.springframework.kafka.core.KafkaTemplate; import org.springframework.stereotype.Service; import java.time.Instant; import java.util.UUID;
@Service
public class TransactionService {
  private final KafkaTemplate<String, TransactionReceivedEvent> kafkaTemplate; private final String topic;
  public TransactionService(KafkaTemplate<String, TransactionReceivedEvent> kafkaTemplate, @Value("${app.kafka.topics.transaction-received}") String topic){ this.kafkaTemplate=kafkaTemplate; this.topic=topic; }
  public TransactionAcceptedResponse accept(TransactionRequest request){
    UUID id=UUID.randomUUID(); TransactionReceivedEvent event=new TransactionReceivedEvent(id, request.accountId().trim(), request.amount(), request.merchant().trim(), request.location().trim().toUpperCase(), Instant.now());
    try { kafkaTemplate.send(topic, id.toString(), event).get(); }
    catch(Exception ex){ throw new KafkaPublishException("Kafka unavailable. Transaction not accepted; fallback option is durable outbox/in-memory demo queue.", ex); }
    return new TransactionAcceptedResponse(id,"ACCEPTED", event.receivedAt());
  }
}
