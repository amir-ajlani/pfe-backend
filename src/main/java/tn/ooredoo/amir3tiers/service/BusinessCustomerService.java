package tn.ooredoo.amir3tiers.service;

import tn.ooredoo.amir3tiers.DTOS.BusinessCustomerRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.BusinessCustomerResponseDTO;

import java.util.List;
import java.util.UUID;


public interface BusinessCustomerService {
    BusinessCustomerResponseDTO create(BusinessCustomerRequestDTO request);
    List<BusinessCustomerResponseDTO> getAll();
    BusinessCustomerResponseDTO getById(UUID id);
    void delete(UUID id);
}