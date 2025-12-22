package com.b2b.customer.domain.model;

import com.b2b.customer.domain.enums.UserStatus;
import com.b2b.customer.domain.exception.InvalidCustomerException;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public record Customer(UUID id,
                       String identityProviderId,
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

    public Customer {
        validateEmail(email);
        validateName(firstName, "firstName");
        validateName(lastName, "lastName");

        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (status == null) {
            status = UserStatus.ACTIVE;
        }
    }

    public static Customer create(
            String identityProviderId,
            String email,
            String firstName,
            String lastName,
            String companyName,
            String taxId,
            String address,
            String phone,
            String city,
            String country
    ) {
        return new Customer(
                null,
                identityProviderId,
                email,
                firstName,
                lastName,
                companyName,
                taxId,
                address,
                phone,
                UserStatus.ACTIVE,
                city,
                country,
                LocalDateTime.now()
        );
    }

    public Customer activate() {
        if (status == UserStatus.ACTIVE) {
            throw new InvalidCustomerException("Customer is already active");
        }
        return new Customer(
                id, identityProviderId, email, firstName, lastName,
                companyName, taxId, address, phone, UserStatus.ACTIVE,
                city, country, createdAt
        );
    }

    public Customer deactivate() {
        if (status == UserStatus.INACTIVE) {
            throw new InvalidCustomerException("Customer is already inactive");
        }
        return new Customer(
                id, identityProviderId, email, firstName, lastName,
                companyName, taxId, address, phone, UserStatus.INACTIVE,
                city, country, createdAt
        );
    }

    public Customer updateProfile(
            String firstName,
            String lastName,
            String phone,
            String address,
            String city,
            String country
    ) {
        return new Customer(
                id, identityProviderId, email,
                firstName != null ? firstName : this.firstName,
                lastName != null ? lastName : this.lastName,
                companyName, taxId,
                address != null ? address : this.address,
                phone != null ? phone : this.phone,
                status,
                city != null ? city : this.city,
                country != null ? country : this.country,
                createdAt
        );
    }

    public boolean isActive() {
        return status == UserStatus.ACTIVE;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    private static void validateEmail(String email) {
        Objects.requireNonNull(email, "Email cannot be null");
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new InvalidCustomerException("Invalid email format: " + email);
        }
    }

    private static void validateName(String name, String fieldName) {
        Objects.requireNonNull(name, fieldName + " cannot be null");
        if (name.isBlank()) {
            throw new InvalidCustomerException(fieldName + " cannot be blank");
        }
        if (name.length() < 2 || name.length() > 100) {
            throw new InvalidCustomerException(fieldName + " must be between 2 and 100 characters");
        }
    }
}
