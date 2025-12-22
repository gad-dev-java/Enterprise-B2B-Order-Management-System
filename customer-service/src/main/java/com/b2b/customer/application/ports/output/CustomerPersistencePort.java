package com.b2b.customer.application.ports.output;

import com.b2b.customer.domain.model.Customer;

public interface CustomerPersistencePort {
    Customer saveCustomer(Customer customer);
    boolean existsCustomerByEmail(String email);
}
