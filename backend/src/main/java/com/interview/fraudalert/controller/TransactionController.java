package com.interview.fraudalert.controller;
import com.interview.fraudalert.dto.*; import com.interview.fraudalert.service.TransactionService; import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/transactions")
public class TransactionController { private final TransactionService service; public TransactionController(TransactionService service){this.service=service;} @PostMapping public ResponseEntity<TransactionAcceptedResponse> create(@Valid @RequestBody TransactionRequest request){ return ResponseEntity.accepted().body(service.accept(request)); } }
