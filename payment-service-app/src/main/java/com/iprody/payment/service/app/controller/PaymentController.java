package com.iprody.payment.service.app.controller;

import com.iprody.payment.service.app.dto.PaymentResponse;
import com.iprody.payment.service.app.service.PaymentService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAll() {
        return ResponseEntity
                .ok(paymentService.getAllPayments());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<PaymentResponse> getPayment(@PathVariable UUID uuid) {
        return ResponseEntity
                .ok()
                .body(paymentService.getPaymentById(uuid));
    }
}
