package com.b2b.customer.infrastructure.adapters.input.rest.model.response;

import com.b2b.customer.domain.enums.UserStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record CustomerResponse(
        UUID id,
        String email,
        String firstName,
        String lastName,
        String companyName,
        String taxId,
        String address,
        String phone,
        UserStatus status,
        String city,
        String country,
        LocalDateTime createdAt
) {
}
