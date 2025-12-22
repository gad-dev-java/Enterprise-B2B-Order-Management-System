package com.b2b.customer.application.ports.input;

import com.b2b.customer.application.ports.input.dto.RegisterCustomerCommand;
import com.b2b.customer.application.ports.input.dto.CustomerDto;

public interface RegisterCustomerUseCase {
    CustomerDto register(RegisterCustomerCommand command);
}
