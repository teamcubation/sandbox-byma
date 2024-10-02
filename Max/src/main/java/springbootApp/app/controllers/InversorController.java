package springbootApp.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootApp.app.apis.InversorApi;
import springbootApp.app.controllers.mappers.InversorMapper;
import springbootApp.app.controllers.DTOs.InversorDTO;
import springbootApp.app.models.Inversor;

import springbootApp.app.services.interfaces.IInversorService;
import springbootApp.app.services.interfaces.IObserverService;

import java.util.List;

@RestController
@RequestMapping("/inversor")
public class InversorController implements InversorApi {

    private static final Logger log = LoggerFactory.getLogger(InversorController.class);
    @Autowired
    private IInversorService inversorService;
    @Autowired
    private IObserverService observerService;

    @PostMapping("/")
    public ResponseEntity<?> registrar(@RequestBody InversorDTO inversorDTO) {
        log.info("intentando registrar inversor...");
        Inversor inversor = InversorMapper.inversorDTOToInversor(inversorDTO);
        inversorService.registrarInversor(inversor);
        return ResponseEntity.ok(inversorDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<?>> obtenerTodos() {
        log.info("intentando obtener inversores...");
        return ResponseEntity.ok(inversorService.consultarTodosLosInversores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorID(@PathVariable Long id){
        log.info("intentando obtener inversor...");
        return ResponseEntity.ok(inversorService.consultarInversor(id));
    }

    @GetMapping("/instrumentos/{id}")
    public ResponseEntity<?> obtenerInstrumentosDeInversor(@PathVariable Long id) {
        log.info("intentando obtener instrumentos de inversor...");
        return ResponseEntity.ok(inversorService.consultarInstrumentosDeInversor(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody InversorDTO inversorDTO) {
        log.info("intentando actualizar inversor...");
        Inversor inversor = InversorMapper.inversorDTOToInversor(inversorDTO);
        return ResponseEntity.ok(inversorService.actualizarInversor(id, inversor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        log.info("intentando eliminar inversor...");
        inversorService.eliminarInversor(id);
        log.info("Inversor eliminado");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/subscribirse/{id}/{idInstrumento}")
    public ResponseEntity<?> suscribir(@PathVariable Long id, @PathVariable Long idInstrumento) {
        log.info("intentando suscribirse...");
        observerService.metodoParaSuscribirse(id, idInstrumento);
        log.info("Suscripción exitosa");
        return ResponseEntity.ok("Suscripción exitosa");
    }

    @PutMapping("/desubscribirse/{id}/{idInstrumento}")
    public ResponseEntity<?> desuscribir(@PathVariable Long id, @PathVariable Long idInstrumento) {
        log.info("intentando desuscribirse...");
        observerService.metodoParaDesuscribirse(id, idInstrumento);
        log.info("Desubscripción exitosa");
        return ResponseEntity.ok("Desubscripción exitosa");
    }
}
