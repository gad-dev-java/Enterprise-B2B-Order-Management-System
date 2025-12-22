package com.b2b.customer.domain.exception;

public class InvalidCustomerException extends RuntimeException{
    public InvalidCustomerException(String message) {
        super(message);
    }
}
