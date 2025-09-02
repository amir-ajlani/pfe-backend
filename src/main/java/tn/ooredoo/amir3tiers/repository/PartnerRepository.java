package tn.ooredoo.amir3tiers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.ooredoo.amir3tiers.entity.Partner;

import java.util.UUID;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
    boolean existsById(Long code);
}