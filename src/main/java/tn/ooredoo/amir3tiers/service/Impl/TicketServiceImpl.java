package tn.ooredoo.amir3tiers.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.ooredoo.amir3tiers.DTOS.TicketRequest;
import tn.ooredoo.amir3tiers.DTOS.TicketResponse;
import tn.ooredoo.amir3tiers.Mappers.TicketMapper;
import tn.ooredoo.amir3tiers.entity.Activation;
import tn.ooredoo.amir3tiers.entity.Ticket;
import tn.ooredoo.amir3tiers.repository.ActivationRepository;
import tn.ooredoo.amir3tiers.repository.TicketRepository;
import tn.ooredoo.amir3tiers.service.TicketService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final ActivationRepository activationRepository;
    private final TicketMapper ticketMapper;

    @Override
    public TicketResponse create(TicketRequest request) {
        Activation activation = null;
        if (request.activationId() != null) {
            activation = activationRepository.findById(request.activationId())
                    .orElseThrow(() -> new EntityNotFoundException("Activation not found"));
        }

        Ticket ticket = ticketMapper.toEntity(request, activation);
        return ticketMapper.toResponse(ticketRepository.save(ticket));
    }

    @Override
    public List<TicketResponse> getAll() {
        return ticketMapper.toResponseList(ticketRepository.findAll());
    }

    @Override
    public TicketResponse getById(UUID id) {
        return ticketRepository.findById(id)
                .map(ticketMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found"));
    }

    @Override
    public void delete(UUID id) {
        ticketRepository.deleteById(id);
    }
}
