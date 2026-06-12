package com.interview.fraudalert.service;
import com.interview.fraudalert.event.FraudEvaluatedEvent; import org.springframework.kafka.annotation.KafkaListener; import org.springframework.stereotype.Service;
@Service
public class AlertConsumer { private final AlertBroadcaster broadcaster; public AlertConsumer(AlertBroadcaster broadcaster){this.broadcaster=broadcaster;} @KafkaListener(topics="${app.kafka.topics.fraud-evaluated}", groupId="alert-service") public void onFraudEvaluated(FraudEvaluatedEvent event){ broadcaster.broadcast(event); } }
