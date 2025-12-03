package com.iprody.payment.service.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "email")
@Builder
public class Payment {

    private Long id;
    private String email;
    private BigDecimal amount;
    private PaymentStatus status;
    private PaymentMethod method;

}
