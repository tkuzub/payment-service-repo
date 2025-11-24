package com.iprody.payment.service.app.service.impl;

import com.iprody.payment.service.app.dto.PaymentResponse;
import com.iprody.payment.service.app.model.PaymentStatus;
import com.iprody.payment.service.app.exception.PaymentNotFoundException;
import com.iprody.payment.service.app.mapper.PaymentDtoMapper;
import com.iprody.payment.service.app.repository.dao.PaymentDao;
import com.iprody.payment.service.app.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import java.util.Objects;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    private PaymentDao paymentDao;
    @Mock
    private PaymentDtoMapper paymentDtoMapper;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    void shouldReturnPaymentResponseByIdWhenPaymentExists() {
        // given
        var payment = TestUtils.generatePayment();
        var paymentResponse = TestUtils.generatePaymentResponse();
        doReturn(Optional.ofNullable(payment)).when(paymentDao)
                .getById(Objects.requireNonNull(payment).getId());
        doReturn(paymentResponse).when(paymentDtoMapper).toPaymentResponse(payment);
        // when
        PaymentResponse response = paymentService.getPaymentById(payment.getId());
        // then
        assertAll(
                () -> assertThat(response).isNotNull(),
                () -> assertThat(response).isEqualTo(paymentResponse)
        );
        verify(paymentDao).getById(payment.getId());
        verify(paymentDtoMapper).toPaymentResponse(payment);
    }

    @Test
    void shouldThrowsExceptionIfPaymentDoesNotExists() {
        // given
        var payment = TestUtils.generatePayment();
        payment.setId(2L);
        doReturn(Optional.empty()).when(paymentDao).getById(payment.getId());
        // then
        assertThatThrownBy(() -> paymentService.getPaymentById(payment.getId()))
                .isInstanceOf(PaymentNotFoundException.class)
                .hasMessageContaining("Payment with id = 2 does not exists");
        verify(paymentDao).getById(payment.getId());
        verifyNoInteractions(paymentDtoMapper);
    }

    @Test
    void addPayment() {
        // given
        var paymentResponse = TestUtils.generatePaymentResponse();
        var paymentRequest = TestUtils.generatePaymentRequest();
        var payment = TestUtils.generatePayment();
        doReturn(payment).when(paymentDtoMapper).toPayment(paymentRequest);
        doReturn(payment).when(paymentDao).save(payment);
        doReturn(paymentResponse).when(paymentDtoMapper).toPaymentResponse(payment);
        // when
        var actualPaymentResponse = paymentService.addPayment(paymentRequest);
        // then
        assertThat(actualPaymentResponse).isNotNull().isEqualTo(paymentResponse);
        verify(paymentDtoMapper).toPayment(paymentRequest);
        verify(paymentDao).save(payment);
        verify(paymentDtoMapper).toPaymentResponse(payment);
    }

    @Test
    void shouldUpdateAndReturnPaymentResponse() {
        // given
        var payment = TestUtils.generatePayment();
        var paymentResponse = TestUtils.generatePaymentResponse();
        doReturn(Optional.ofNullable(payment)).when(paymentDao).getById(Objects.requireNonNull(payment).getId());
        payment.setStatus(PaymentStatus.CREATED);

        paymentResponse.setStatus(PaymentStatus.CREATED);
        doReturn(paymentResponse).when(paymentDtoMapper).toPaymentResponse(payment);
        // when
        var actualResponse = paymentService.updateStatus(1L, PaymentStatus.CREATED);
        // then
        assertThat(actualResponse.getStatus()).isEqualTo(PaymentStatus.CREATED);
        verify(paymentDao).save(payment);
    }

    @Test
    void shouldThrowExceptionIfPaymentDoesNotExist() {
        // given
        var payment = TestUtils.generatePayment();
        payment.setId(2L);
        doReturn(Optional.empty()).when(paymentDao).getById(payment.getId());
        // then
        assertThatThrownBy(() -> paymentService.getPaymentById(payment.getId()))
                .isInstanceOf(PaymentNotFoundException.class)
                .hasMessageContaining("Payment with id = 2 does not exists");
        verify(paymentDao).getById(payment.getId());
        verify(paymentDtoMapper, never()).toPaymentResponse(payment);
        verify(paymentDao, never()).save(payment);
    }
}