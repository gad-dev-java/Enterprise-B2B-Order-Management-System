package com.b2b.customer.infrastructure.adapters.input.rest.model.request;


public record RegisterCustomerRequest(
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
