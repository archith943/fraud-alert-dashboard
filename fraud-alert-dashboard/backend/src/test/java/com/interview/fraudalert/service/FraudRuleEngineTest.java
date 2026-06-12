package com.interview.fraudalert.service;
import com.interview.fraudalert.event.TransactionReceivedEvent; import com.interview.fraudalert.model.RiskLevel; import org.junit.jupiter.api.Test; import java.math.BigDecimal; import java.time.Instant; import java.util.UUID; import static org.assertj.core.api.Assertions.assertThat;
class FraudRuleEngineTest { FraudRuleEngine engine=new FraudRuleEngine();
  @Test void highRiskTakesPrecedenceOverLocation(){ var e=event("15000","IN"); assertThat(engine.evaluate(e).riskLevel()).isEqualTo(RiskLevel.HIGH_RISK); }
  @Test void mediumRiskForForeignLocation(){ assertThat(engine.evaluate(event("500","IN")).riskLevel()).isEqualTo(RiskLevel.MEDIUM_RISK); }
  @Test void lowRiskForNormalUsTransaction(){ assertThat(engine.evaluate(event("500","US")).riskLevel()).isEqualTo(RiskLevel.LOW_RISK); }
  private TransactionReceivedEvent event(String amount,String loc){ return new TransactionReceivedEvent(UUID.randomUUID(),"A1",new BigDecimal(amount),"M",loc, Instant.now()); }
}
