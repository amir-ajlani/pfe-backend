package tn.ooredoo.amir3tiers.service;

import tn.ooredoo.amir3tiers.DTOS.BoxActivationRequest;
import tn.ooredoo.amir3tiers.DTOS.BoxActivationResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BoxActivationService {
    BoxActivationResponse create(BoxActivationRequest request);
    List<BoxActivationResponse> getAll();
    BoxActivationResponse getById(UUID id);

    void delete(UUID id) throws Exception;
}
