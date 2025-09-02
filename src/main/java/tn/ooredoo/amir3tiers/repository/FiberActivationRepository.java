package tn.ooredoo.amir3tiers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.ooredoo.amir3tiers.entity.FiberActivation;
import tn.ooredoo.amir3tiers.enums.ActivationStatus;

import java.util.List;
import java.util.UUID;

public interface FiberActivationRepository extends JpaRepository<FiberActivation, UUID> {
    List<FiberActivation> findByCustomerId(UUID customerId);
    List<FiberActivation> findByStatus(ActivationStatus status);
}
