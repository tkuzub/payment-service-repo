package com.iprody.payment.service.app.utils;

import com.iprody.payment.service.app.dto.PaymentRequest;
import com.iprody.payment.service.app.dto.PaymentResponse;
import com.iprody.payment.service.app.model.Payment;
import com.iprody.payment.service.app.model.PaymentMethod;
import com.iprody.payment.service.app.model.PaymentStatus;

import java.math.BigDecimal;

public class TestUtils {

    public static Payment generatePayment() {
        return Payment.builder()
                .id(1L)
                .email("user@gmail.com")
                .amount(BigDecimal.TEN)
                .status(PaymentStatus.CREATED)
                .method(PaymentMethod.CARD)
                .build();
    }

    public static PaymentRequest generatePaymentRequest() {
        return PaymentRequest.builder()
                .amount(BigDecimal.TEN)
                .email("user@gmail.com")
                .method(PaymentMethod.CARD)
                .status(PaymentStatus.CREATED)
                .build();
    }

    public static PaymentResponse generatePaymentResponse() {
        return PaymentResponse.builder()
                .id(1L)
                .email("user@gmail.com")
                .amount(BigDecimal.TEN)
                .status(PaymentStatus.CREATED)
                .method(PaymentMethod.CARD)
                .build();
    }
}




