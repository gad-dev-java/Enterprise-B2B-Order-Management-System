package com.b2b.customer.application.ports.input.dto;


public record RegisterCustomerCommand(
        String email,
        String password,
        String firstName,
        String lastName,
        String companyName,
        String taxId,
        String address,
        String phone,
        String city,
        String country
) {
}
