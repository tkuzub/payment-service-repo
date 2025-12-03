package com.iprody.payment.service.app.repository.dao;

import com.iprody.payment.service.app.exception.PaymentNotFoundException;
import com.iprody.payment.service.app.model.Payment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemoryPaymentDao implements PaymentDao {

    private final Map<Long, Payment> paymentDtoMap = new HashMap<>();
    private static long isSequence = 1L;

    @Override
    public List<Payment> getAll() {
        return new ArrayList<>(paymentDtoMap.values());
    }

    @Override
    public Optional<Payment> getById(Long id) {
        final Payment payment = paymentDtoMap.get(id);
        if (payment == null) {
            throw new PaymentNotFoundException(String.format("can't find account with id = %d", id));
        }
        return Optional.of(payment);
    }

    @Override
    public Payment save(final Payment payment) {
        if (payment.getId() == null) {
            payment.setId(isSequence++);
        }
        paymentDtoMap.put(payment.getId(), payment);
        return payment;
    }

    public void clear() {
        paymentDtoMap.clear();
        isSequence = 1L;
    }
}
