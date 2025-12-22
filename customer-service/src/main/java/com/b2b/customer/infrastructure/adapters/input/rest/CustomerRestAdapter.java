package com.b2b.customer.infrastructure.adapters.input.rest;

import com.b2b.customer.application.ports.input.RegisterCustomerUseCase;
import com.b2b.customer.application.ports.input.dto.CustomerDto;
import com.b2b.customer.application.ports.input.dto.RegisterCustomerCommand;
import com.b2b.customer.infrastructure.adapters.input.rest.mapper.CustomerRestMapper;
import com.b2b.customer.infrastructure.adapters.input.rest.model.request.RegisterCustomerRequest;
import com.b2b.customer.infrastructure.adapters.input.rest.model.response.CustomerResponse;
import com.b2b.customer.infrastructure.adapters.input.rest.model.response.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerRestAdapter {
    private final RegisterCustomerUseCase registerCustomerUseCase;
    private final CustomerRestMapper customerRestMapper;

    @PostMapping
    public ResponseEntity<DataResponse<CustomerResponse>> registerCustomer(@RequestBody RegisterCustomerRequest request) {
        RegisterCustomerCommand command = customerRestMapper.toCommand(request);
        CustomerDto customerDto = registerCustomerUseCase.register(command);
        CustomerResponse responseBody = customerRestMapper.toResponse(customerDto);

        URI location = URI.create("/customers/" + responseBody.id());

        DataResponse<CustomerResponse> dataResponse = DataResponse.<CustomerResponse>builder()
                .status(HttpStatus.CREATED.value())
                .message("Customer registered successfully")
                .data(responseBody)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.created(location).body(dataResponse);
    }
}
