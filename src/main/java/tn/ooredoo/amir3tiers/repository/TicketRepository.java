package tn.ooredoo.amir3tiers.repository;


import org.springframework.data.domain.Pageable;  // Correct import
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.ooredoo.amir3tiers.entity.Ticket;
import tn.ooredoo.amir3tiers.enums.TicketStatus;


import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    @EntityGraph(attributePaths = {"customer", "createdBy"})
    Page<Ticket> findByStatus(TicketStatus status, Pageable pageable);

    List<Ticket> findByStatus(TicketStatus status);
    List<Ticket> findByIsTreatedFalse();
}