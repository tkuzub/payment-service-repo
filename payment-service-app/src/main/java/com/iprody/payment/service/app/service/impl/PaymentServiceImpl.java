package com.iprody.payment.service.app.service.impl;

import com.iprody.payment.service.app.exception.ServiceException;
import com.iprody.payment.service.app.model.Payment;
import com.iprody.payment.service.app.dto.PaymentRequest;
import com.iprody.payment.service.app.dto.PaymentResponse;
import com.iprody.payment.service.app.model.PaymentStatus;
import com.iprody.payment.service.app.mapper.PaymentDtoMapper;
import com.iprody.payment.service.app.service.PaymentService;
import com.iprody.payment.service.app.repository.dao.PaymentDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.iprody.payment.service.app.exception.ErrorMessage.PAYMENT_NOT_EXIST;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentDao paymentDao;
    private final PaymentDtoMapper paymentDtoMapper;

    @Override
    public PaymentResponse getPaymentById(Long id) {
        final var payment = paymentDao.getById(id)
            .orElseThrow(() -> new ServiceException(PAYMENT_NOT_EXIST, id));
        return paymentDtoMapper.toPaymentResponse(payment);
    }

    @Override
    public List<PaymentResponse> getAllPayments() {
        final List<Payment> payments = paymentDao.getAll();
        return payments.stream()
            .filter(Objects::nonNull)
            .map(paymentDtoMapper::toPaymentResponse)
            .toList();
    }

    @Override
    public PaymentResponse addPayment(PaymentRequest paymentRequest) {
        final var payment = paymentDtoMapper.toPayment(paymentRequest);
        return paymentDtoMapper.toPaymentResponse(paymentDao.save(payment));
    }

    @Override
    public PaymentResponse updateStatus(Long id, PaymentStatus status) {
        final var payment = paymentDao.getById(id)
            .orElseThrow(() -> new ServiceException(PAYMENT_NOT_EXIST, id));
        payment.setStatus(status);
        paymentDao.save(payment);
        return paymentDtoMapper.toPaymentResponse(payment);
    }
}
