package com.interview.fraudalert.service;
import com.interview.fraudalert.event.FraudEvaluatedEvent; import org.slf4j.*; import org.springframework.stereotype.Service; import org.springframework.web.servlet.mvc.method.annotation.SseEmitter; import java.io.IOException; import java.util.List; import java.util.concurrent.CopyOnWriteArrayList;
@Service
public class AlertBroadcaster {
  private static final Logger log=LoggerFactory.getLogger(AlertBroadcaster.class); private final List<SseEmitter> emitters=new CopyOnWriteArrayList<>();
  public SseEmitter subscribe(){ SseEmitter emitter=new SseEmitter(0L); emitters.add(emitter); emitter.onCompletion(()->emitters.remove(emitter)); emitter.onTimeout(()->emitters.remove(emitter)); emitter.onError(e->emitters.remove(emitter)); try { emitter.send(SseEmitter.event().name("connected").data("ok")); } catch(IOException ignored){} return emitter; }
  public void broadcast(FraudEvaluatedEvent alert){
    for(SseEmitter emitter: emitters){ try { emitter.send(SseEmitter.event().name("fraud-alert").data(alert)); } catch(Exception ex){ log.warn("Removing stale SSE emitter"); emitters.remove(emitter); } }
  }
  public int activeConnections(){ return emitters.size(); }
}
