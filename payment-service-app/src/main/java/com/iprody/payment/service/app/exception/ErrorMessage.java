package com.iprody.payment.service.app.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    // Common
    NULL_ID(HttpStatus.BAD_REQUEST, "ID cannot be null"),
    NEGATIVE_ID(HttpStatus.BAD_REQUEST, "ID cannot be 0 or negative"),

    // Payments
    PAYMENT_NOT_EXIST(HttpStatus.NOT_FOUND, "Payment with id = %s does not exist");

    private final HttpStatus status;
    private final String message;

}
