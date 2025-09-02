package tn.ooredoo.amir3tiers.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.ooredoo.amir3tiers.DTOS.FixeActivationRequest;
import tn.ooredoo.amir3tiers.DTOS.FixeActivationResponse;
import tn.ooredoo.amir3tiers.service.FixeActivationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/activations/fixe")
@RequiredArgsConstructor
public class FixeActivationController {

    private final FixeActivationService service;

    @PostMapping
    public ResponseEntity<FixeActivationResponse> create(@RequestBody @Valid FixeActivationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<FixeActivationResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FixeActivationResponse> getById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}