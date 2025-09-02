package tn.ooredoo.amir3tiers.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.ooredoo.amir3tiers.DTOS.ContractRequest;
import tn.ooredoo.amir3tiers.DTOS.ContractResponse;
import tn.ooredoo.amir3tiers.entity.Activation;
import tn.ooredoo.amir3tiers.entity.BoxActivation;
import tn.ooredoo.amir3tiers.entity.FiberActivation;
import tn.ooredoo.amir3tiers.entity.FixeActivation;
import tn.ooredoo.amir3tiers.enums.ContractStatus;
import tn.ooredoo.amir3tiers.service.ContractService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contracts")
@RequiredArgsConstructor
@Tag(name = "Contract")
public class ContractController {

    private final ContractService service;

    @PostMapping
    public ResponseEntity<ContractResponse> create(@RequestBody ContractRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public List<ContractResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractResponse> getById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @GetMapping("/by-customer/{customerId}")
    public List<ContractResponse> getByCustomer(@PathVariable(name = "customerId") UUID customerId) {
        return service.getByCustomerId(customerId);
    }

    @GetMapping("/by-status/{status}")
    public List<ContractResponse> getByStatus(@PathVariable(name = "status") ContractStatus status) {
        return service.getByStatus(status);
    }

    @GetMapping("/by-activation-type")
    public ResponseEntity<List<ContractResponse>> findByActivationType(@RequestParam(name = "type") String type) {
        Class<? extends Activation> clazz = switch (type.toLowerCase()) {
            case "box" -> BoxActivation.class;
            case "fiber" -> FiberActivation.class;
            case "fixe" -> FixeActivation.class;
            default -> throw new IllegalArgumentException("Unknown activation type");
        };
        return ResponseEntity.ok(service.getByActivationType(clazz));
    }
}
