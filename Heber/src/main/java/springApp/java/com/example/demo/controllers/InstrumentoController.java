package springApp.java.com.example.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import springApp.java.com.example.demo.services.InstrumentoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/instrumento") // Este seria el math base del controller
public class InstrumentoController {

    @Autowired
    private InstrumentoService instrumentoService;

    @GetMapping("/hola")
    public String instrumento() {
        return "Instrumento";
    }

    // GET URL: localhost:8081/service-service
    @RequestMapping("/hello-service/{id}")
    public String helloService(@PathVariable("id") Long id) {
        System.out.println("el id es: " + id);
        return instrumentoService.helloService();
    }


    // GET URL: localhost:8081/hello-repository
    @RequestMapping("/hello-repository")
    public String helloRepository() {
        return instrumentoService.helloRepository();
    }



}
