package tn.ooredoo.amir3tiers.service;

import tn.ooredoo.amir3tiers.DTOS.TicketRequest;
import tn.ooredoo.amir3tiers.DTOS.TicketResponse;

import java.util.List;
import java.util.UUID;

public interface TicketService {
    public TicketResponse create(TicketRequest request);
    public List<TicketResponse> getAll();
    public TicketResponse getById(UUID id);
    public void delete(UUID id);
}
