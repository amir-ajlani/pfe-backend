package tn.ooredoo.amir3tiers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.ooredoo.amir3tiers.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByMsisdn(String msisdn);
    boolean existsByMsisdn(String msisdn);
    @Query("SELECT c.blacklisted FROM Customer c WHERE c.id = :id")
    Optional<Boolean> findBlacklistedStatusById(UUID id);
}