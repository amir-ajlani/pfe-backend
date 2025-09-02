package tn.ooredoo.amir3tiers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.ooredoo.amir3tiers.entity.IndividualCustomer;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer, UUID> {
    Optional<IndividualCustomer> findByMsisdn(String msisdn);
}