package tn.ooredoo.amir3tiers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.ooredoo.amir3tiers.entity.FixeActivation;

import java.util.UUID;

public interface FixeActivationRepository  extends JpaRepository<FixeActivation, UUID> {
}
