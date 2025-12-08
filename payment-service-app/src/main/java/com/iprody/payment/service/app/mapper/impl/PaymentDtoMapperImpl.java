package com.iprody.payment.service.app.mapper.impl;

import com.iprody.payment.service.app.model.Payment;
import com.iprody.payment.service.app.dto.PaymentRequest;
import com.iprody.payment.service.app.dto.PaymentResponse;
import com.iprody.payment.service.app.mapper.PaymentDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class PaymentDtoMapperImpl implements PaymentDtoMapper {

    @Override
    public PaymentResponse toPaymentResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getGuid())
                .amount(payment.getAmount())
                .status(payment.getStatus())
                .build();
    }

    @Override
    public Payment toPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .amount(paymentRequest.getAmount())
                .status(paymentRequest.getStatus())
                .build();
    }
}
