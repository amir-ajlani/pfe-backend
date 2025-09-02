package tn.ooredoo.amir3tiers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.ooredoo.amir3tiers.DTOS.PointOfSaleRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.PointOfSaleResponseDTO;
import tn.ooredoo.amir3tiers.service.PointOfSaleService;

import java.util.List;

@RestController
@RequestMapping("/api/point-of-sales")
@RequiredArgsConstructor
public class PointOfSaleController {

    private final PointOfSaleService service;

    @PostMapping
    public ResponseEntity<PointOfSaleResponseDTO> create(@RequestBody PointOfSaleRequestDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{code}")
    public ResponseEntity<PointOfSaleResponseDTO> update(@PathVariable(name = "code") String code, @RequestBody PointOfSaleRequestDTO dto) {
        return ResponseEntity.ok(service.update(code, dto));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> delete(@PathVariable(name = "code") String code) {
        service.delete(code);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{code}")
    public ResponseEntity<PointOfSaleResponseDTO> getById(@PathVariable(name = "code") String code) {
        return ResponseEntity.ok(service.getByCode(code));
    }

    @GetMapping
    public ResponseEntity<List<PointOfSaleResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}