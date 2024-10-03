package springbootMigracion.java.com.example.demo.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;

@OpenAPIDefinition(
        info = @Info(
                title = "API INSTRUMENTOS FINANCIEROS",
                description = "Aplicacion que permite realizar un CRUD de Instrumentos financieros e Inversores",
                version = "1.0.0"
        ),
        servers = {
                @Server(
                        description = "DEV SERVER",
                        url = "http://localhost:8080",
                        variables = {
                        @ServerVariable(name = "puerto", defaultValue = "${swagger.port}")
                }
                )
        }
)
public class SwaggerConfig {
}
