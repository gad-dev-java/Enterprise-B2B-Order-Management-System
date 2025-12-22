package com.b2b.customer.infrastructure.adapters.input.rest.mapper;

import com.b2b.customer.application.ports.input.dto.CustomerDto;
import com.b2b.customer.application.ports.input.dto.RegisterCustomerCommand;
import com.b2b.customer.infrastructure.adapters.input.rest.model.request.RegisterCustomerRequest;
import com.b2b.customer.infrastructure.adapters.input.rest.model.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.WARN)
public interface CustomerRestMapper {

    RegisterCustomerCommand toCommand(RegisterCustomerRequest request);

    CustomerResponse toResponse(CustomerDto customerDto);
}
