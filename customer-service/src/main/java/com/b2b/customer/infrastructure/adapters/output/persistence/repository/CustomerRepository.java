package com.b2b.customer.infrastructure.adapters.output.persistence.repository;

import com.b2b.customer.infrastructure.adapters.output.persistence.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {
    boolean existsByEmail(String email);
}
