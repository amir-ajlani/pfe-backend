package tn.ooredoo.amir3tiers.DTOS;

import tn.ooredoo.amir3tiers.enums.TicketStatus;
import tn.ooredoo.amir3tiers.enums.TicketType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record TicketResponse(
        UUID id,
        String reference,
        TicketType type,
        TicketStatus status,
        LocalDateTime createdAt,
        Boolean isTreated,
        UUID activationId,
        List<LigTicketResponse> lignes
) {}