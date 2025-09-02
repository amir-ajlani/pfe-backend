package tn.ooredoo.amir3tiers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.ooredoo.amir3tiers.DTOS.LoginRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.LoginResponseDTO;
import tn.ooredoo.amir3tiers.config.security.JwtService;
import tn.ooredoo.amir3tiers.entity.UserAccount;
import tn.ooredoo.amir3tiers.repository.UserAccountRepository;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserAccountRepository userAccountRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        try {
            System.out.println(request);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );

            // Charger l'utilisateur depuis la base
            UserAccount user = userAccountRepository.findByUsername(request.username())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Générer le token JWT
            String token = jwtService.generateToken(user,user.getUsername(),user.getUserType());

            return ResponseEntity.ok(new LoginResponseDTO(token, user.getUserType(), user.getUsername()));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}
