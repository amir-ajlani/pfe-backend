package tn.ooredoo.amir3tiers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.ooredoo.amir3tiers.DTOS.IndividualCustomerRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.IndividualCustomerResponseDTO;
import tn.ooredoo.amir3tiers.service.IndividualCustomerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/individual-customers")
@RequiredArgsConstructor
public class IndividualCustomerController {

    private final IndividualCustomerService service;

    @PostMapping
    public ResponseEntity<IndividualCustomerResponseDTO> create(@RequestBody IndividualCustomerRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<IndividualCustomerResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IndividualCustomerResponseDTO> getById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
