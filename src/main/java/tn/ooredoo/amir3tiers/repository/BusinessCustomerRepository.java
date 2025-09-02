package tn.ooredoo.amir3tiers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.ooredoo.amir3tiers.entity.BusinessCustomer;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BusinessCustomerRepository extends JpaRepository<BusinessCustomer, UUID> {
    Optional<BusinessCustomer> findByMsisdn(String msisdn);
}