package com.b2b.customer.infrastructure.adapters.output.persistence;

import com.b2b.customer.application.ports.output.CustomerPersistencePort;
import com.b2b.customer.domain.model.Customer;
import com.b2b.customer.infrastructure.adapters.output.persistence.entities.CustomerEntity;
import com.b2b.customer.infrastructure.adapters.output.persistence.mapper.CustomerMapper;
import com.b2b.customer.infrastructure.adapters.output.persistence.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerPersistenceAdapter implements CustomerPersistencePort {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Customer saveCustomer(Customer customer) {
        CustomerEntity customerEntity = customerMapper.toEntity(customer);
        CustomerEntity customerSaved = customerRepository.save(customerEntity);
        return customerMapper.toDomain(customerSaved);
    }

    @Override
    public boolean existsCustomerByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }
}
