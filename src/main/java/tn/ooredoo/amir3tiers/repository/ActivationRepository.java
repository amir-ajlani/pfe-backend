package tn.ooredoo.amir3tiers.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.ooredoo.amir3tiers.entity.Activation;
import tn.ooredoo.amir3tiers.enums.ActivationStatus;

import java.util.List;
import java.util.UUID;

@Repository
public interface ActivationRepository extends JpaRepository<Activation, UUID> {
    @EntityGraph(attributePaths = "customer")
    List<Activation> findByCustomerId(UUID customerId);

    @Query("SELECT a FROM Activation a WHERE TYPE(a) = :type AND a.status = :status")
    <T extends Activation> List<T> findByTypeAndStatus(
            @Param("type") Class<T> type,
            @Param("status") ActivationStatus status);
}