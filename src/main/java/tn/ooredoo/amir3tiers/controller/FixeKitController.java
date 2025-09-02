package tn.ooredoo.amir3tiers.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.ooredoo.amir3tiers.DTOS.FixeKitBatchCreateRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.FixeKitRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.FixeKitResponseDTO;
import tn.ooredoo.amir3tiers.service.PosProductStockService;

import java.util.List;

@RestController
@RequestMapping("/api/fixe-kits")
@RequiredArgsConstructor
@Tag(name = "Fixe Kits")
public class FixeKitController {

    private final PosProductStockService service;

    @PostMapping("/batch")
    public ResponseEntity<List<FixeKitResponseDTO>> createBatch(@RequestBody FixeKitBatchCreateRequestDTO request) {
        return ResponseEntity.ok(service.createBatch(request));
    }

    @GetMapping("/by-product-pos")
    public ResponseEntity<List<FixeKitResponseDTO>> getByProductAndOptionalPOS(
            @RequestParam(name = "productCode") String productCode,
            @RequestParam(name ="pointOfSaleCode", required = false) String pointOfSaleCode
    ) {
        return ResponseEntity.ok(service.getByProductAndOptionalPOS(productCode, pointOfSaleCode));
    }

    @PostMapping
    public ResponseEntity<FixeKitResponseDTO> create(@RequestBody FixeKitRequestDTO request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FixeKitResponseDTO> update(
            @PathVariable(name = "id") String id,
            @RequestBody FixeKitRequestDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<FixeKitResponseDTO> getById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(service.getById(id));
    }


    @GetMapping
    public ResponseEntity<List<FixeKitResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

}
