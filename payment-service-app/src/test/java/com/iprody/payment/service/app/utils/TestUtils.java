package com.iprody.payment.service.app.utils;

import com.iprody.payment.service.app.dto.PaymentRequest;
import com.iprody.payment.service.app.dto.PaymentResponse;
import com.iprody.payment.service.app.model.Payment;
import com.iprody.payment.service.app.model.PaymentStatus;

import java.math.BigDecimal;
import java.util.UUID;

public class TestUtils {

    public static Payment generatePayment() {
        return Payment.builder()
                .guid(UUID.randomUUID())
                .amount(BigDecimal.TEN)
                .status(PaymentStatus.APPROVED)
                .build();
    }

    public static PaymentRequest generatePaymentRequest() {
        return PaymentRequest.builder()
                .amount(BigDecimal.TEN)
                .status(PaymentStatus.RECEIVED)
                .build();
    }

    public static PaymentResponse generatePaymentResponse() {
        return PaymentResponse.builder()
                .id(UUID.randomUUID())
                .amount(BigDecimal.TEN)
                .status(PaymentStatus.APPROVED)
                .build();
    }
}




