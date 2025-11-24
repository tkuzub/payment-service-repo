package com.iprody.payment.service.app.repository.dao;

import com.iprody.payment.service.app.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentDao {

    List<Payment> getAll();

    Optional<Payment> getById(Long id);

    Payment save(Payment payment);
}
