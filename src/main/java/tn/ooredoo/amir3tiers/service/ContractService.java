package tn.ooredoo.amir3tiers.service;

import tn.ooredoo.amir3tiers.DTOS.ContractRequest;
import tn.ooredoo.amir3tiers.DTOS.ContractResponse;
import tn.ooredoo.amir3tiers.entity.Activation;
import tn.ooredoo.amir3tiers.enums.ContractStatus;

import java.util.List;
import java.util.UUID;

public interface ContractService {
    public ContractResponse create(ContractRequest request);
    public List<ContractResponse> getAll();
    public ContractResponse getById(UUID id);
    public void delete(UUID id);
    public List<ContractResponse> getByCustomerId(UUID customerId);
    public List<ContractResponse> getByStatus(ContractStatus status);

    List<ContractResponse> getByActivationType(Class<? extends Activation> clazz);
}
