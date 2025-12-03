package com.iprody.payment.service.app.controller;
import com.iprody.payment.service.app.dto.PaymentRequest;
import com.iprody.payment.service.app.dto.PaymentResponse;
import com.iprody.payment.service.app.model.PaymentStatus;
import com.iprody.payment.service.app.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPayment(@PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body(paymentService.getPaymentById(id));
    }

    @PostMapping()
    public ResponseEntity<PaymentResponse> create(@RequestBody PaymentRequest paymentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentService.addPayment(paymentRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponse> updateStatus(@PathVariable Long id, @RequestParam PaymentStatus status) {
        return ResponseEntity
                .ok()
                .body(paymentService.updateStatus(id, status));
    }
}
