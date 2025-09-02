package tn.ooredoo.amir3tiers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.ooredoo.amir3tiers.DTOS.LoginRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.LoginResponseDTO;
import tn.ooredoo.amir3tiers.DTOS.UserAccountRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.UserAccountResponseDTO;
import tn.ooredoo.amir3tiers.service.UserAccountService;

import java.util.List;

@RestController
@RequestMapping("/api/user-accounts")
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService service;

    @PostMapping
    public ResponseEntity<UserAccountResponseDTO> create(@RequestBody UserAccountRequestDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserAccountResponseDTO> update(@PathVariable(name = "username") String username,
                                                         @RequestBody UserAccountRequestDTO dto) {
        return ResponseEntity.ok(service.update(username, dto));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> delete(@PathVariable(name = "username") String username) {
        service.delete(username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserAccountResponseDTO> getById(@PathVariable(name = "username") String username) {
        return ResponseEntity.ok(service.getById(username));
    }

    @GetMapping
    public ResponseEntity<List<UserAccountResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        return ResponseEntity.ok(service.login(loginRequest));
    }
}