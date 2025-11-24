package com.iprody.payment.service.app.service.impl;

import com.iprody.payment.service.app.model.Payment;
import com.iprody.payment.service.app.dto.PaymentRequest;
import com.iprody.payment.service.app.dto.PaymentResponse;
import com.iprody.payment.service.app.model.PaymentStatus;
import com.iprody.payment.service.app.exception.PaymentNotFoundException;
import com.iprody.payment.service.app.mapper.PaymentDtoMapper;
import com.iprody.payment.service.app.service.PaymentService;
import com.iprody.payment.service.app.repository.dao.PaymentDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentDao paymentDao;
    private final PaymentDtoMapper paymentDtoMapper;

    @Override
    public PaymentResponse getPaymentById(Long id) {
        var payment = paymentDao.getById(id)
                .orElseThrow(() -> new PaymentNotFoundException(String.format("Payment with id = %d does not exists", id)));
        return paymentDtoMapper.toPaymentResponse(payment);
    }

    @Override
    public List<PaymentResponse> getAllPayments() {
        List<Payment> payments = paymentDao.getAll();
        return payments.stream()
                .filter(Objects::nonNull)
                .map(paymentDtoMapper::toPaymentResponse)
                .toList();
    }

    @Override
    public PaymentResponse addPayment(PaymentRequest paymentRequest) {
        var payment = paymentDtoMapper.toPayment(paymentRequest);
        return paymentDtoMapper.toPaymentResponse(paymentDao.save(payment));
    }

    @Override
    public PaymentResponse updateStatus(Long id, PaymentStatus status) {
        var payment = paymentDao.getById(id)
                .orElseThrow(() -> new PaymentNotFoundException(String.format("Payment with id = %d does not exists", id)));
        payment.setStatus(status);
        paymentDao.save(payment);
        return paymentDtoMapper.toPaymentResponse(payment);
    }
}
