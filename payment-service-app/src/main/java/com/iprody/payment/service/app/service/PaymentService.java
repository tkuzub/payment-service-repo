package com.iprody.payment.service.app.service;

import com.iprody.payment.service.app.dto.PaymentRequest;
import com.iprody.payment.service.app.dto.PaymentResponse;
import com.iprody.payment.service.app.model.PaymentStatus;

import java.util.List;

public interface PaymentService {

    PaymentResponse getPaymentById(Long id);

    List<PaymentResponse> getAllPayments();

    PaymentResponse addPayment(PaymentRequest payment);

    PaymentResponse updateStatus(Long id, PaymentStatus status);
}
