package tn.ooredoo.amir3tiers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.ooredoo.amir3tiers.DTOS.TicketRequest;
import tn.ooredoo.amir3tiers.DTOS.TicketResponse;
import tn.ooredoo.amir3tiers.service.TicketService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponse> create(@RequestBody TicketRequest request) {
        return ResponseEntity.ok(ticketService.create(request));
    }

    @GetMapping
    public List<TicketResponse> getAll() {
        return ticketService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> getById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(ticketService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") UUID id) {
        ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }
}