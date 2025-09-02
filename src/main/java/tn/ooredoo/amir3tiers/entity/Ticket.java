package tn.ooredoo.amir3tiers.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.ooredoo.amir3tiers.enums.TicketStatus;
import tn.ooredoo.amir3tiers.enums.TicketType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue
    private UUID id;

    private String reference;

    @Enumerated(EnumType.STRING)
    private TicketType type;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    private LocalDateTime createdAt = LocalDateTime.now();

    private Boolean isTreated = false;

    // Lien vers une activation polymorphe (Fixe ou Fiber)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activation_id")
    private Activation activation;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LigTicket> lignes = new ArrayList<>();
}