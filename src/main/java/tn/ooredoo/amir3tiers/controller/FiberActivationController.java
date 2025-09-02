package tn.ooredoo.amir3tiers.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.ooredoo.amir3tiers.DTOS.FiberActivationRequest;
import tn.ooredoo.amir3tiers.DTOS.FiberActivationResponse;
import tn.ooredoo.amir3tiers.enums.ActivationStatus;
import tn.ooredoo.amir3tiers.service.FiberActivationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/activations/fiber")
@RequiredArgsConstructor
public class FiberActivationController {

    private final FiberActivationService service;

    @PostMapping
    public ResponseEntity<FiberActivationResponse> create(@RequestBody @Valid FiberActivationRequest request) {
        FiberActivationResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<FiberActivationResponse>> getAll() {
        List<FiberActivationResponse> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FiberActivationResponse> getById(@PathVariable(name ="id") UUID id) {
        FiberActivationResponse response = service.getById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name ="id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/by-customer")
    public ResponseEntity<List<FiberActivationResponse>> getByCustomerId(@RequestParam(name ="customerId") UUID customerId) {
        return ResponseEntity.ok(service.findByCustomerId(customerId));
    }

    @GetMapping("/search/by-status")
    public ResponseEntity<List<FiberActivationResponse>> getByStatus(@RequestParam(name ="status") ActivationStatus status) {
        return ResponseEntity.ok(service.findByStatus(status));
    }
}
