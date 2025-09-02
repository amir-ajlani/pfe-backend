package tn.ooredoo.amir3tiers.service;

import tn.ooredoo.amir3tiers.DTOS.IndividualCustomerRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.IndividualCustomerResponseDTO;

import java.util.List;
import java.util.UUID;

public interface IndividualCustomerService {
    IndividualCustomerResponseDTO create(IndividualCustomerRequestDTO request);
    List<IndividualCustomerResponseDTO> getAll();
    IndividualCustomerResponseDTO getById(UUID id);
    void delete(UUID id);
}
