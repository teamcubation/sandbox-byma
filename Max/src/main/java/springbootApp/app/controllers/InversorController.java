package  springbootApp.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  springbootApp.app.controllers.mappers.InversorMapper;
import  springbootApp.app.DTOs.InversorDTO;
import  springbootApp.app.models.Inversor;
import  springbootApp.app.services.InversorService;
import  springbootApp.app.services.ObserverService;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerInversor(@PathVariable Long id) throws Exception {
            log.info("intentando obtener inversor...");
            return ResponseEntity.ok(inversorService.consultarInversor(id));
    }

    @GetMapping("/instrumentos/{id}")
    public ResponseEntity<?> obtenerInstrumentosDeInversor(@PathVariable Long id) throws Exception {
            log.info("intentando obtener instrumentos de inversor...");
            return ResponseEntity.ok(inversorService.consultarInstrumentosDeInversor(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarInversor(@PathVariable Long id, @RequestBody InversorDTO inversorDTO) throws Exception {
            log.info("intentando actualizar inversor...");
            Inversor inversor = InversorMapper.inversorDTOToInversor(inversorDTO);
            return ResponseEntity.ok(inversorService.actualizarInversor(id, inversor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarInversor(@PathVariable Long id) throws Exception{
            log.info("intentando eliminar inversor...");
            inversorService.eliminarInversor(id);
            return ResponseEntity.noContent().build();
        }

    @PutMapping("/subscribirse/{id}/{idInstrumento}")
    public ResponseEntity<?> suscribirse(@PathVariable Long id, @PathVariable Long idInstrumento) throws Exception{
            log.info("intentando suscribirse...");
            observerService.metodoParaSuscribirse(id, idInstrumento);
            log.info("Suscripción exitosa");
            return ResponseEntity.ok("Suscripción exitosa");
    }

    @PutMapping("/desubscribirse/{id}/{idInstrumento}")
    public ResponseEntity<?> desuscribirse(@PathVariable Long id, @PathVariable Long idInstrumento) throws Exception{
            log.info("intentando desuscribirse...");
            observerService.metodoParaDesuscribirse(id, idInstrumento);
            System.out.println("Desuscripción exitosa");
            return ResponseEntity.ok("Desuscripción exitosa");
    }
}
