package springbootApp.java.controllers;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootApp.java.controllers.mappers.InversorMapper;
import springbootApp.java.exceptions.InstrumentoDuplicadoException;
import springbootApp.java.exceptions.InstrumentoNoEncontradoException;
import springbootApp.java.exceptions.InversorExistenteException;
import springbootApp.java.exceptions.InversorNoEncontradoException;
import springbootApp.java.DTOs.InversorDTO;
import springbootApp.java.models.InstrumentoFinanciero;
import springbootApp.java.models.Inversor;
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
    public ResponseEntity<?> registrarInversor(@RequestBody InversorDTO inversorDTO) throws Exception{
            log.info("intentando registrar inversor...");
            Inversor inversor = InversorMapper.inversorDTOToInversor(inversorDTO);
            inversorService.registrarInversor(inversor);
            return ResponseEntity.ok(inversorDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<?>> obtenerTodosLosInversores() throws Exception{
            log.info("intentando obtener inversores...");
            return ResponseEntity.ok(inversorService.consultarTodosLosInversores());
    }

    @GetMapping("/{dni}")
    public ResponseEntity<?> obtenerInversor(@PathVariable String dni) throws Exception {
            log.info("intentando obtener inversor...");
            return ResponseEntity.ok(inversorService.consultarInversor(dni));
    }

    @GetMapping("/instrumentos/{dni}")
    public ResponseEntity<?> obtenerInstrumentosDeInversor(@PathVariable String dni) throws Exception {
            log.info("intentando obtener instrumentos de inversor...");
            return ResponseEntity.ok(inversorService.consultarInstrumentosDeInversor(dni));
    }


    @PutMapping("/{dni}")
    public ResponseEntity<?> actualizarInversor(@PathVariable String dni, @RequestBody InversorDTO inversorDTO) throws Exception {
            log.info("intentando actualizar inversor...");
            Inversor inversor = InversorMapper.inversorDTOToInversor(inversorDTO);
            return ResponseEntity.ok(inversorService.actualizarInversor(dni, inversor));
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<?> eliminarInversor(@PathVariable String dni) throws Exception{
            log.info("intentando eliminar inversor...");
            inversorService.eliminarInversor(dni);
            return ResponseEntity.noContent().build();
        }

    @PutMapping("/subscribirse/{dni}/{nombreInstrumento}")
    public ResponseEntity<?> suscribirse(@PathVariable String dni, @PathVariable String nombreInstrumento) throws Exception{
            log.info("intentando suscribirse...");
            observerService.metodoParaSuscribirse(dni, nombreInstrumento);
            log.info("Suscripción exitosa");
            return ResponseEntity.ok("Suscripción exitosa");
    }

    @PutMapping("/desubscribirse/{dni}/{nombreInstrumento}")
    public ResponseEntity<?> desuscribirse(@PathVariable String dni, @PathVariable String nombreInstrumento) throws Exception{
            log.info("intentando desuscribirse...");
            observerService.metodoParaDesuscribirse(dni, nombreInstrumento);
            System.out.println("Desuscripción exitosa");
            return ResponseEntity.ok("Desuscripción exitosa");
    }
}
