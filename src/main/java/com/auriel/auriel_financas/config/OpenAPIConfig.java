package com.auriel.auriel_financas.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    private static final String SECURITY_SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // 1. Define o esquema de segurança (como o Swagger vai se autenticar)
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME, 
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                
                // 2. Aplica o esquema de segurança globalmente
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME)); 
    }
}