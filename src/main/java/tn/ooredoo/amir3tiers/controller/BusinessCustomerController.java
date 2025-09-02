package tn.ooredoo.amir3tiers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.ooredoo.amir3tiers.DTOS.BusinessCustomerRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.BusinessCustomerResponseDTO;
import tn.ooredoo.amir3tiers.service.BusinessCustomerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/business-customers")
@RequiredArgsConstructor
public class BusinessCustomerController {

    private final BusinessCustomerService service;

    @PostMapping
    public ResponseEntity<BusinessCustomerResponseDTO> create(@RequestBody BusinessCustomerRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<BusinessCustomerResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessCustomerResponseDTO> getById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}