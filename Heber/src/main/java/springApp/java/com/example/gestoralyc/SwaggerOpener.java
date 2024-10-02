package springApp.java.com.example.gestoralyc;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URI;

@Component
public class SwaggerOpener implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String swaggerUrl = "http://localhost:5000/swagger-ui.html"; // La URL de Swagger
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(new URI(swaggerUrl));
        } else {
            System.out.println("Abre Swagger manualmente en: " + swaggerUrl);
        }
    }
}
