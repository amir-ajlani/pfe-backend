package tn.ooredoo.amir3tiers.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.ooredoo.amir3tiers.DTOS.BoxActivationRequest;
import tn.ooredoo.amir3tiers.DTOS.BoxActivationResponse;
import tn.ooredoo.amir3tiers.service.BoxActivationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/activations/box")
@RequiredArgsConstructor
public class BoxActivationController {

    private final BoxActivationService service;

    @PostMapping
    public ResponseEntity<BoxActivationResponse> create(@RequestBody @Valid BoxActivationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<BoxActivationResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoxActivationResponse> getById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") UUID id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}