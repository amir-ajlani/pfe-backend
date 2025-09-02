package tn.ooredoo.amir3tiers.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.ooredoo.amir3tiers.entity.Activation;
import tn.ooredoo.amir3tiers.entity.Contract;
import tn.ooredoo.amir3tiers.enums.ContractStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContractRepository extends JpaRepository<Contract, UUID> {
    @EntityGraph(attributePaths = {"activations", "customer"})
    Optional<Contract> findDetailedById(UUID id);

    boolean existsByContractNumber(String contractNumber);

    List<Contract> findByCustomerId(UUID customerId);
    List<Contract> findByStatus(ContractStatus status);

    @Query("SELECT c FROM Contract c WHERE TYPE(c.activation) = :activationType")
    List<Contract> findByActivationType(@Param("activationType") Class<? extends Activation> activationType);
}