package com.iprody.payment.service.app.mapper;

import com.iprody.payment.service.app.model.Payment;
import com.iprody.payment.service.app.dto.PaymentRequest;
import com.iprody.payment.service.app.dto.PaymentResponse;

public interface PaymentDtoMapper {

    PaymentResponse toPaymentResponse(Payment payment);

    Payment toPayment(PaymentRequest paymentRequest);

}
