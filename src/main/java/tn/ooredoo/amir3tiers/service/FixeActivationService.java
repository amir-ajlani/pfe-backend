package tn.ooredoo.amir3tiers.service;

import tn.ooredoo.amir3tiers.DTOS.FixeActivationRequest;
import tn.ooredoo.amir3tiers.DTOS.FixeActivationResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FixeActivationService {
    FixeActivationResponse create(FixeActivationRequest request);
    List<FixeActivationResponse> getAll();
    FixeActivationResponse getById(UUID id);

    void delete(UUID id);
}