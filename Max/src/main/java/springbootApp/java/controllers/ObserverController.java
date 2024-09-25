package springbootApp.java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootApp.java.exceptions.InstrumentoDuplicadoException;
import springbootApp.java.exceptions.InstrumentoNoEncontradoException;
import springbootApp.java.exceptions.InversorExistenteException;
import springbootApp.java.exceptions.InversorNoEncontradoException;
import springbootApp.java.services.ObserverService;

@RestController
@RequestMapping("/observer")
public class ObserverController {

    @Autowired
    private ObserverService observerService;

    @PostMapping("/subscribirse/{dni}/{nombreInstrumento}")
    public ResponseEntity<String> suscribirse(@PathVariable String dni, @PathVariable String nombreInstrumento) {
        try {
            observerService.metodoParaSuscribirse(dni, nombreInstrumento);
            System.out.println("Suscripción exitosa");
            return ResponseEntity.ok("Suscripción exitosa");
        } catch (InversorNoEncontradoException | InstrumentoDuplicadoException |
                 InversorExistenteException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/desubscribirse")
    public ResponseEntity<String> desuscribirse(@PathVariable String dni, @PathVariable String nombreInstrumento ) {
        try {
            observerService.metodoParaDesuscribirse(dni, nombreInstrumento);
            System.out.println("Desuscripción exitosa");
            return ResponseEntity.ok("Desuscripción exitosa");
        } catch (InversorNoEncontradoException | InstrumentoNoEncontradoException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
