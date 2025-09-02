package tn.ooredoo.amir3tiers.DTOS;

import tn.ooredoo.amir3tiers.enums.TicketStatus;
import tn.ooredoo.amir3tiers.enums.TicketType;

import java.util.List;
import java.util.UUID;

public record TicketRequest(
        String reference,
        TicketType type,
        TicketStatus status,
        UUID activationId,
        List<LigTicketRequest> lignes
) {}
