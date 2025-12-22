package com.b2b.customer.infrastructure.adapters.output.persistence.mapper;

import com.b2b.customer.domain.model.Customer;
import com.b2b.customer.infrastructure.adapters.output.persistence.entities.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.WARN)
public interface CustomerMapper {

    @Mapping(target = "keycloakId", source = "identityProviderId")
    @Mapping(target = "businessPhone", source = "phone")
    @Mapping(target = "addressLine", source = "address")
    CustomerEntity toEntity(Customer customer);

    @Mapping(target = "identityProviderId", source = "keycloakId")
    @Mapping(target = "phone", source = "businessPhone")
    @Mapping(target = "address", source = "addressLine")
    Customer toDomain(CustomerEntity customer);
}
