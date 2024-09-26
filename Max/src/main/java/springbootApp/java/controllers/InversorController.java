package springbootApp.java.controllers;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootApp.java.exceptions.InstrumentoDuplicadoException;
import springbootApp.java.exceptions.InstrumentoNoEncontradoException;
import springbootApp.java.exceptions.InversorExistenteException;
import springbootApp.java.exceptions.InversorNoEncontradoException;
import springbootApp.java.DTOs.InversorDTO;
import springbootApp.java.services.InversorService;
import springbootApp.java.services.ObserverService;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/inversor")
public class InversorController {

    private static final Logger log = LoggerFactory.getLogger(InversorController.class);
    @Autowired
    private InversorService inversorService;
    @Autowired
    private ObserverService observerService;

    @PostMapping("/")
    public ResponseEntity<?> registrarInversor(@RequestBody InversorDTO inversor) {
        try {
            log.info("intentando registrar inversor...");
            inversorService.registrarInversor(inversor.getNombre(), inversor.getDni());
            return ResponseEntity.ok(inversor);
        } catch (Exception e) {
            log.info("Error al registrar inversor: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<?>> obtenerTodosLosInversores() {
        try {
            log.info("intentando obtener inversores...");
            return ResponseEntity.ok(inversorService.consultarTodosLosInversores());
        } catch (Exception e) {
            log.info("Error al obtener inversores: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/instrumentos/{dni}")
    public ResponseEntity<?> obtenerInstrumentosDeInversor(@PathVariable String dni) throws InversorNoEncontradoException {
        try {
            log.info("intentando obtener instrumentos de inversor...");
            return ResponseEntity.ok(inversorService.consultarInstrumentosDeInversor(dni));
        } catch (Exception e) {
            log.info("Error al obtener instrumentos de inversor: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/{dni}")
    public ResponseEntity<?> actualizarInversor(@PathVariable String dni, @RequestBody InversorDTO inversor) throws InversorNoEncontradoException {
        try {
            log.info("intentando actualizar inversor...");
            inversorService.actualizarInversor(dni, inversor);
            return ResponseEntity.ok(inversor);
        } catch (Exception e) {
            log.info("Error al actualizar inversor: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<?> eliminarInversor(@PathVariable String dni) throws InversorNoEncontradoException {
        try {
            log.info("intentando eliminar inversor...");
            inversorService.eliminarInversor(dni);
            return ResponseEntity.ok("Inversor eliminado");
        } catch (InversorNoEncontradoException e) {
            log.info("Error al eliminar inversor: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/subscribirse/{dni}/{nombreInstrumento}")
    public ResponseEntity<?> suscribirse(@PathVariable String dni, @PathVariable String nombreInstrumento) {
        try {
            log.info("intentando suscribirse...");
            observerService.metodoParaSuscribirse(dni, nombreInstrumento);
            log.info("Suscripción exitosa");
            return ResponseEntity.ok("Suscripción exitosa");
        } catch (InversorNoEncontradoException | InstrumentoDuplicadoException |
                 InversorExistenteException e) {
            log.info("Error al suscribirse: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.info("Error al suscribirse: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/desubscribirse/{dni}/{nombreInstrumento}")
    public ResponseEntity<?> desuscribirse(@PathVariable String dni, @PathVariable String nombreInstrumento ) {
        try {
            log.info("intentando desuscribirse...");
            observerService.metodoParaDesuscribirse(dni, nombreInstrumento);
            System.out.println("Desuscripción exitosa");
            return ResponseEntity.ok("Desuscripción exitosa");
        } catch (InversorNoEncontradoException | InstrumentoNoEncontradoException e) {
            log.info("Error al desuscribirse: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.info("Error al desuscribirse: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
