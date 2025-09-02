package tn.ooredoo.amir3tiers.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI partnerActivationOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Partner Activation API")
                        .version("1.0")
                        .description("API pour la gestion des partenaires, PDV et kits Fixe")
                        .contact(new Contact()
                                .name("Amir Ajlani")
                                .email("tech-support@ooredoo.tn")
                                .url("https://support.ooredoo.tn"))
                        .license(new License()
                                .name("Licence Ooredoo")
                                .url("https://www.ooredoo.tn/legal")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .name("bearerAuth")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Enter JWT Bearer token")
                        )
                );
    }
}
