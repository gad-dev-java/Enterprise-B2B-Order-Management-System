package com.b2b.customer.application.ports.service;

import com.b2b.customer.application.ports.input.RegisterCustomerUseCase;
import com.b2b.customer.application.ports.input.dto.RegisterCustomerCommand;
import com.b2b.customer.application.ports.input.dto.CustomerDto;
import com.b2b.customer.application.ports.output.CustomerPersistencePort;
import com.b2b.customer.application.ports.output.IdentityProviderPort;
import com.b2b.customer.domain.exception.CustomerAlreadyExistsException;
import com.b2b.customer.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterCustomerService implements RegisterCustomerUseCase {
    private final CustomerPersistencePort customerPersistencePort;
    private final IdentityProviderPort identityProviderPort;

    @Override
    @Transactional
    public CustomerDto register(RegisterCustomerCommand command) {
        if (customerPersistencePort.existsCustomerByEmail(command.email())) {
            throw new CustomerAlreadyExistsException("Customer with email " + command.email() + " already exists.");
        }

        String keycloakId = identityProviderPort.createUser(
                command.email(),
                command.password(),
                command.firstName(),
                command.lastName()
        );

        Customer customer = Customer.create(
                keycloakId,
                command.email(),
                command.firstName(),
                command.lastName(),
                command.companyName(),
                command.taxId(),
                command.address(),
                command.phone(),
                command.city(),
                command.country()
        );

        Customer savedCustomer = customerPersistencePort.saveCustomer(customer);


        return toDto(savedCustomer);
    }

    public CustomerDto toDto(Customer customer) {
        return new CustomerDto(
                customer.id(),
                customer.email(),
                customer.firstName(),
                customer.lastName(),
                customer.companyName(),
                customer.taxId(),
                customer.address(),
                customer.phone(),
                customer.status(),
                customer.city(),
                customer.country(),
                customer.createdAt()
        );
    }
}
