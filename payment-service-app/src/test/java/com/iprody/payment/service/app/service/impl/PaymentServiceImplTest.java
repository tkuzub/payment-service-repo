package com.iprody.payment.service.app.service.impl;

import com.iprody.payment.service.app.dto.PaymentResponse;
import com.iprody.payment.service.app.exception.ServiceException;
import com.iprody.payment.service.app.mapper.PaymentDtoMapper;
import com.iprody.payment.service.app.repository.PaymentRepository;
import com.iprody.payment.service.app.utils.TestUtils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.EMPTY_LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private PaymentDtoMapper paymentDtoMapper;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    void shouldReturnPaymentResponseByIdWhenPaymentExists() {
        // given
        var payment = TestUtils.generatePayment();
        var paymentResponse = TestUtils.generatePaymentResponse();
        paymentResponse.setId(payment.getGuid());
        doReturn(Optional.of(payment)).when(paymentRepository)
                .findById(Objects.requireNonNull(payment).getGuid());
        doReturn(paymentResponse).when(paymentDtoMapper).toPaymentResponse(payment);
        // when
        PaymentResponse response = paymentService.getPaymentById(payment.getGuid());
        // then
        assertAll(
                () -> assertThat(response).isNotNull(),
                () -> assertThat(response).isEqualTo(paymentResponse)
        );
        verify(paymentRepository).findById(payment.getGuid());
        verify(paymentDtoMapper).toPaymentResponse(payment);
    }

    @Test
    void shouldThrowsExceptionIfPaymentDoesNotExists() {
        // given
        var payment = TestUtils.generatePayment();
        payment.setGuid(UUID.randomUUID());
        doReturn(Optional.empty()).when(paymentRepository).findById(payment.getGuid());
        // then
        assertThatThrownBy(() -> paymentService.getPaymentById(payment.getGuid()))
                .isInstanceOf(ServiceException.class)
                .hasMessageContaining(String.format("Payment with id = %s does not exist", payment.getGuid()));
        verify(paymentRepository).findById(payment.getGuid());
        verifyNoInteractions(paymentDtoMapper);
    }

    @Test
    void shouldReturnAllPaymentsMappedToPaymentsResponse() {
        // given
        var payment = TestUtils.generatePayment();
        doReturn(List.of(payment)).when(paymentRepository).findAll();
        // when
        List<PaymentResponse> allPayments = paymentService.getAllPayments();
        // then
        assertThat(allPayments).hasSize(1);
        verify(paymentDtoMapper).toPaymentResponse(payment);
    }

    @Test
    void shouldReturnEmptyListWhenNoPaymentExist() {
        doReturn(EMPTY_LIST).when(paymentRepository).findAll();
        // when
        List<PaymentResponse> allPayments = paymentService.getAllPayments();
        // then
        assertThat(allPayments).hasSize(0);
    }
}
