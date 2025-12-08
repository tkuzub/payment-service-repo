package com.iprody.payment.service.app.dto;

import com.iprody.payment.service.app.model.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PaymentRequest {
    private  BigDecimal amount;
    private  PaymentStatus status;
}
