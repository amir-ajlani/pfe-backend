package tn.ooredoo.amir3tiers.service;

import tn.ooredoo.amir3tiers.DTOS.FiberActivationRequest;
import tn.ooredoo.amir3tiers.DTOS.FiberActivationResponse;
import tn.ooredoo.amir3tiers.enums.ActivationStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FiberActivationService {
    FiberActivationResponse create(FiberActivationRequest request);
    List<FiberActivationResponse> getAll();
    FiberActivationResponse getById(UUID id);

    void delete(UUID id);


    List<FiberActivationResponse> findByCustomerId(UUID customerId);

    List<FiberActivationResponse> findByStatus(ActivationStatus status);
}