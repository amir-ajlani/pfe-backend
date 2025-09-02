package tn.ooredoo.amir3tiers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.ooredoo.amir3tiers.DTOS.PartnerRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.PartnerResponseDTO;
import tn.ooredoo.amir3tiers.service.PartnerService;

import java.util.List;

@RestController
@RequestMapping("/api/partners")
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerService service;

    @PostMapping
    public ResponseEntity<PartnerResponseDTO> create(@RequestBody PartnerRequestDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartnerResponseDTO> update(@PathVariable(name = "id") Long id, @RequestBody PartnerRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartnerResponseDTO> getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<PartnerResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}