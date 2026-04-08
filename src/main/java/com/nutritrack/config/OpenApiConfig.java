package com.nutritrack.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
  @Bean
  public OpenAPI openAPI() {
    var schemeName = "bearerAuth";
    return new OpenAPI()
        .info(new Info().title("NutriTrack API").version("v1")
            .description("Spring Boot NutriTrack API. Use Swagger UI while the backend is running on port 8080."))
        .components(new Components().addSecuritySchemes(schemeName,
            new SecurityScheme()
                .name(schemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")))
        .addSecurityItem(new SecurityRequirement().addList(schemeName));
  }
}

