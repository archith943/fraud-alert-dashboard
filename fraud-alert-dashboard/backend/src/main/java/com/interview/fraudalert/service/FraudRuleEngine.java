package com.interview.fraudalert.service;
import com.interview.fraudalert.event.TransactionReceivedEvent; import com.interview.fraudalert.model.RiskLevel; import org.springframework.stereotype.Component; import java.math.BigDecimal;
@Component
public class FraudRuleEngine {
  private static final BigDecimal HIGH_RISK_THRESHOLD = new BigDecimal("10000");
  private static final String HOME_LOCATION = "US";
  public Evaluation evaluate(TransactionReceivedEvent event){
    if(event.amount().compareTo(HIGH_RISK_THRESHOLD) > 0) return new Evaluation(RiskLevel.HIGH_RISK, "Amount exceeds 10000");
    if(!HOME_LOCATION.equalsIgnoreCase(event.location())) return new Evaluation(RiskLevel.MEDIUM_RISK, "Transaction outside home location");
    return new Evaluation(RiskLevel.LOW_RISK, "No risk rule matched");
  }
  public record Evaluation(RiskLevel riskLevel, String reason) {}
}
