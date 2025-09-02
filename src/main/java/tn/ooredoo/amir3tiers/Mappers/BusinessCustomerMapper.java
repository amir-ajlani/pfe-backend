package tn.ooredoo.amir3tiers.Mappers;

import org.springframework.stereotype.Component;
import tn.ooredoo.amir3tiers.DTOS.BusinessCustomerRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.BusinessCustomerResponseDTO;
import tn.ooredoo.amir3tiers.entity.BusinessCustomer;

@Component
public class BusinessCustomerMapper {

    public BusinessCustomer toEntity(BusinessCustomerRequestDTO request) {
        BusinessCustomer entity = new BusinessCustomer();
        entity.setMsisdn(request.msisdn());
        entity.setBusinessName(request.businessName());
        entity.setTradeRegisterNumber(request.tradeRegisterNumber());
        return entity;
    }

    public BusinessCustomerResponseDTO toResponse(BusinessCustomer entity) {
        return new BusinessCustomerResponseDTO(
                entity.getId(),
                entity.getMsisdn(),
                entity.getCreatedAt(),
                entity.getBlacklisted(),
                entity.getBusinessName(),
                entity.getTradeRegisterNumber()
        );
    }
}