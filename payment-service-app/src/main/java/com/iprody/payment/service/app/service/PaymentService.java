package com.iprody.payment.service.app.service;

import com.iprody.payment.service.app.dto.PaymentResponse;

import java.util.List;
import java.util.UUID;

public interface PaymentService {

    PaymentResponse getPaymentById(UUID uuid);

    List<PaymentResponse> getAllPayments();
}
