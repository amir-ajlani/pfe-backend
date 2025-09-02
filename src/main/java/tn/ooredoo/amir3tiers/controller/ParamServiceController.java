package tn.ooredoo.amir3tiers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.ooredoo.amir3tiers.DTOS.ParamServiceDto;
import tn.ooredoo.amir3tiers.DTOS.ParamServiceResponseDTO;
import tn.ooredoo.amir3tiers.service.ParamServiceService;

import java.util.List;

@RestController
@RequestMapping("/api/param-services")
@RequiredArgsConstructor
public class ParamServiceController {

    private final ParamServiceService service;

    @PostMapping
    public ResponseEntity<ParamServiceResponseDTO> create(@RequestBody ParamServiceDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ParamServiceResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParamServiceResponseDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<ParamServiceResponseDTO> getByTmcodeSncode(
            @RequestParam String tmcode,
            @RequestParam String sncode
    ) {
        return ResponseEntity.ok(service.findByTmcodeAndSncode(tmcode, sncode));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParamServiceResponseDTO> update(
            @PathVariable Integer id,
            @RequestBody ParamServiceDto dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Extra endpoint for creating with tmcode+sncode
    @PostMapping("/create-with-codes")
    public ResponseEntity<ParamServiceResponseDTO> createWithCodes(
            @RequestParam String tmcode,
            @RequestParam String sncode,
            @RequestBody ParamServiceDto dto
    ) {
        return ResponseEntity.ok(service.createWithTmcodeSncode(tmcode, sncode, dto));
    }
}
