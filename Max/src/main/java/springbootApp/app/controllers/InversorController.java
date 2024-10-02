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
    public static final String REGISTRAR_INVERSOR = "intentando registrar inversor...";
    public static final String OBTENER_INVERSORES = "intentando obtener inversores...";
    public static final String OBTENER_INVERSOR = "intentando obtener inversor...";
    public static final String INSTRUMENTOS_DE_INVERSOR = "intentando obtener instrumentos de inversor...";
    public static final String ACTUALIZAR_INVERSOR = "intentando actualizar inversor...";
    public static final String ELIMINAR_INVERSOR = "intentando eliminar inversor...";
    public static final String INVERSOR_ELIMINADO = "Inversor eliminado";
    public static final String INTENTANDO_SUSCRIBIRSE = "intentando suscribirse...";
    public static final String SUSCRIPCION_EXITOSA = "Suscripción exitosa";
    public static final String INTENTANDO_DESUSCRIBIRSE = "intentando desuscribirse...";
    public static final String DESUBSCRIPCION_EXITOSA = "Desubscripción exitosa";
    @Autowired
    private IInversorService inversorService;
    @Autowired
    private IObserverService observerService;

    @PostMapping("/")
    public ResponseEntity<?> registrar(@RequestBody InversorDTO inversorDTO) {
        log.info(REGISTRAR_INVERSOR);
        Inversor inversor = InversorMapper.inversorDTOToInversor(inversorDTO);
        inversorService.registrarInversor(inversor);
        return ResponseEntity.ok(inversorDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<?>> obtenerTodos() {
        log.info(OBTENER_INVERSORES);
        return ResponseEntity.ok(inversorService.consultarTodosLosInversores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorID(@PathVariable Long id){
        log.info(OBTENER_INVERSOR);
        return ResponseEntity.ok(inversorService.consultarInversor(id));
    }

    @GetMapping("/instrumentos/{id}")
    public ResponseEntity<?> obtenerInstrumentosDeInversor(@PathVariable Long id) {
        log.info(INSTRUMENTOS_DE_INVERSOR);
        return ResponseEntity.ok(inversorService.consultarInstrumentosDeInversor(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody InversorDTO inversorDTO) {
        log.info(ACTUALIZAR_INVERSOR);
        Inversor inversor = InversorMapper.inversorDTOToInversor(inversorDTO);
        return ResponseEntity.ok(inversorService.actualizarInversor(id, inversor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        log.info(ELIMINAR_INVERSOR);
        inversorService.eliminarInversor(id);
        log.info(INVERSOR_ELIMINADO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/subscribirse/{id}/{idInstrumento}")
    public ResponseEntity<?> suscribir(@PathVariable Long id, @PathVariable Long idInstrumento) {
        log.info(INTENTANDO_SUSCRIBIRSE);
        observerService.metodoParaSuscribirse(id, idInstrumento);
        log.info(SUSCRIPCION_EXITOSA);
        return ResponseEntity.ok(SUSCRIPCION_EXITOSA);
    }

    @PutMapping("/desubscribirse/{id}/{idInstrumento}")
    public ResponseEntity<?> desuscribir(@PathVariable Long id, @PathVariable Long idInstrumento) {
        log.info(INTENTANDO_DESUSCRIBIRSE);
        observerService.metodoParaDesuscribirse(id, idInstrumento);
        log.info(DESUBSCRIPCION_EXITOSA);
        return ResponseEntity.ok(DESUBSCRIPCION_EXITOSA);
    }
}