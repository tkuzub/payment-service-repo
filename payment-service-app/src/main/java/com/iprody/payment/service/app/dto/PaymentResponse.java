package com.iprody.payment.service.app.dto;

import com.iprody.payment.service.app.model.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PaymentResponse {
    private UUID id;
    private BigDecimal amount;
    private PaymentStatus status;
}
