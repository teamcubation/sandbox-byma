package com.example.teamcubation.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Gestión de Bonos Financieros",
                version = "1.0",
                description = "Documentación de la API para la gestión de bonos y otros instrumentos financieros",
                contact = @Contact(
                        name = "Soporte de API",
                        email = "soporte@api.com",
                        url = "https://api.com/soporte"
                ),
                license = @License(
                        name = "Licencia Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"
                )
        )
)
public class SwaggerConfig {
}
