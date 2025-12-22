package com.b2b.customer.application.ports.input.dto;

import com.b2b.customer.domain.enums.UserStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record CustomerDto(UUID id,
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
                          LocalDateTime createdAt) {
}
