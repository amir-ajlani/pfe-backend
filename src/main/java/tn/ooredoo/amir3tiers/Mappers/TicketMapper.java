package tn.ooredoo.amir3tiers.Mappers;

import org.springframework.stereotype.Component;
import tn.ooredoo.amir3tiers.DTOS.LigTicketRequest;
import tn.ooredoo.amir3tiers.DTOS.LigTicketResponse;
import tn.ooredoo.amir3tiers.DTOS.TicketRequest;
import tn.ooredoo.amir3tiers.DTOS.TicketResponse;
import tn.ooredoo.amir3tiers.entity.Activation;
import tn.ooredoo.amir3tiers.entity.LigTicket;
import tn.ooredoo.amir3tiers.entity.Ticket;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class TicketMapper {

    public Ticket toEntity(TicketRequest dto, Activation activation) {
        if (dto == null) return null;

        Ticket ticket = new Ticket();
        ticket.setReference(dto.reference());
        ticket.setType(dto.type());
        ticket.setStatus(dto.status());
        ticket.setActivation(activation);
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setIsTreated(false);

        if (dto.lignes() != null) {
            List<LigTicket> lignes = dto.lignes().stream()
                    .map(ligDto -> toLigEntity(ligDto, ticket))
                    .toList();
            ticket.setLignes(lignes);
        }

        return ticket;
    }

    public TicketResponse toResponse(Ticket entity) {
        if (entity == null) return null;

        return new TicketResponse(
                entity.getId(),
                entity.getReference(),
                entity.getType(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getIsTreated(),
                entity.getActivation() != null ? entity.getActivation().getId() : null,
                toLigResponseList(entity.getLignes())
        );
    }

    public List<TicketResponse> toResponseList(List<Ticket> list) {
        if (list == null) return List.of();
        return list.stream().map(this::toResponse).toList();
    }

    public LigTicket toLigEntity(LigTicketRequest dto, Ticket parent) {
        if (dto == null) return null;
        return LigTicket.builder()
                .kitCode(dto.kitCode())
                .ticket(parent)
                .build();
    }

    public LigTicketResponse toLigResponse(LigTicket entity) {
        if (entity == null) return null;
        return new LigTicketResponse(entity.getId(), entity.getKitCode());
    }

    public List<LigTicketResponse> toLigResponseList(List<LigTicket> lignes) {
        if (lignes == null) return List.of();
        return lignes.stream().map(this::toLigResponse).toList();
    }
}