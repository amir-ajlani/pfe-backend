package tn.ooredoo.amir3tiers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.ooredoo.amir3tiers.entity.BoxActivation;

import java.util.UUID;

public interface BoxActivationRepository extends JpaRepository<BoxActivation, UUID> {}
