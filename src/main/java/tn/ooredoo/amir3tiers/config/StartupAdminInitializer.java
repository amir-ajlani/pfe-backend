package tn.ooredoo.amir3tiers.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tn.ooredoo.amir3tiers.entity.UserAccount;
import tn.ooredoo.amir3tiers.repository.UserAccountRepository;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class StartupAdminInitializer implements CommandLineRunner {

    private final UserAccountRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (repository.existsById("admin")) {
            System.out.println("Admin account already exists.");
        } else {
            UserAccount admin = UserAccount.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("*amir2025")) // mot de passe encodé
                    .email("admin@example.com")
                    .userType("BO") // BackOffice
                    .status("ACTIVE")
                    .createdAt(new Date())
                    .build();
            repository.save(admin);
            System.out.println("Default admin account created with username=admin");
        }
    }
}
