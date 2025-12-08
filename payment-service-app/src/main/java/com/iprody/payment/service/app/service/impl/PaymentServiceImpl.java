package com.iprody.payment.service.app.service.impl;

import com.iprody.payment.service.app.dto.PaymentResponse;
import com.iprody.payment.service.app.exception.ServiceException;
import com.iprody.payment.service.app.mapper.PaymentDtoMapper;
import com.iprody.payment.service.app.model.Payment;
import com.iprody.payment.service.app.repository.PaymentRepository;
import com.iprody.payment.service.app.service.PaymentService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.iprody.payment.service.app.exception.ErrorMessage.PAYMENT_NOT_EXIST;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentDtoMapper paymentDtoMapper;

    @Override
    public PaymentResponse getPaymentById(UUID uuid) {
        final var payment = paymentRepository.findById(uuid)
            .orElseThrow(() -> new ServiceException(PAYMENT_NOT_EXIST, uuid));
        return paymentDtoMapper.toPaymentResponse(payment);
    }

    @Override
    public List<PaymentResponse> getAllPayments() {
        final List<Payment> payments = paymentRepository.findAll();
        return payments.stream()
            .filter(Objects::nonNull)
            .map(paymentDtoMapper::toPaymentResponse)
            .toList();
    }
}
