package tn.ooredoo.amir3tiers.Mappers;

import org.springframework.stereotype.Component;
import tn.ooredoo.amir3tiers.DTOS.IndividualCustomerRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.IndividualCustomerResponseDTO;
import tn.ooredoo.amir3tiers.entity.IndividualCustomer;

@Component
public class IndividualCustomerMapper {

    public IndividualCustomer toEntity(IndividualCustomerRequestDTO request) {
        IndividualCustomer entity = new IndividualCustomer();
        entity.setMsisdn(request.msisdn());
        entity.setFirstName(request.firstName());
        entity.setLastName(request.lastName());
        entity.setDateOfBirth(request.dateOfBirth());
        entity.setIdNumber(request.idNumber());
        return entity;
    }

    public IndividualCustomerResponseDTO toResponse(IndividualCustomer entity) {
        return new IndividualCustomerResponseDTO(
                entity.getId(),
                entity.getMsisdn(),
                entity.getCreatedAt(),
                entity.getBlacklisted(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getDateOfBirth(),
                entity.getIdNumber()
        );
    }
}
