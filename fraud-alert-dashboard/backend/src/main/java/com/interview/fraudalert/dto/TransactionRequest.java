package com.interview.fraudalert.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record TransactionRequest(
  @NotBlank(message="accountId is required") String accountId,
  @NotNull(message="amount is required") @DecimalMin(value="0.01", message="amount must be greater than 0") BigDecimal amount,
  @NotBlank(message="merchant is required") String merchant,
  @NotBlank(message="location is required") String location) {}
