package tn.ooredoo.amir3tiers.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import tn.ooredoo.amir3tiers.entity.Governorate;
import tn.ooredoo.amir3tiers.repository.GovernorateRepository;

import java.io.InputStream;
import java.util.List;

@Configuration
public class GovernorateInitializer {

    @Bean
    ApplicationRunner initGovernorates(GovernorateRepository repository) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new ClassPathResource("config/governorates.json").getInputStream();

            List<Governorate> governorates = mapper.readValue(inputStream, new TypeReference<List<Governorate>>() {});

            for (Governorate g : governorates) {
                if (!repository.existsById(g.getCode())) {
                    repository.save(g);
                    System.out.println("Inserted missing governorate: " + g.getName());
                }
            }
        };
    }
}