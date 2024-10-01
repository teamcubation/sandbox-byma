package springbootMigracion.java.com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootMigracion.java.com.example.demo.dto.InstrumentoDTO;
import springbootMigracion.java.com.example.demo.model.InstrumentoFinanciero;
import springbootMigracion.java.com.example.demo.service.IInstrumentoFinancieroService;

import java.util.List;

@RestController
@RequestMapping("/api/instrumentos")
@Slf4j
public class InstrumentoFinancieroController implements IInstrumentoFinancieroApi {

    @Autowired
    private IInstrumentoFinancieroService instrumentoFinancieroService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarInstrumento(@RequestBody InstrumentoDTO instrumentoDTO) throws Exception {
        log.info("Solicitud para agregar instrumento: {}", instrumentoDTO);
        InstrumentoFinanciero instrumento = instrumentoFinancieroService.registrarInstrumento(instrumentoDTO);
        log.info("Instrumento agregado exitosamente: {}", instrumento);
        return ResponseEntity.status(HttpStatus.CREATED).body(instrumento);
    }

    @GetMapping
    public ResponseEntity<List<InstrumentoFinanciero>> listarInstrumentos() {
        List<InstrumentoFinanciero> instrumentos = instrumentoFinancieroService.listarTodosLosInstrumentos();
        log.info("Instrumentos listados: {}", instrumentos.size());
        return ResponseEntity.ok(instrumentos);
    }

    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<InstrumentoFinanciero>> listarInstrumentosPorNombre(@PathVariable String nombre) {
        List<InstrumentoFinanciero> instrumentosPorNombre = instrumentoFinancieroService.listarInstrumentosPorNombre(nombre);
        log.info("Instrumentos encontrados por nombre: {}", instrumentosPorNombre.size());
        return ResponseEntity.ok(instrumentosPorNombre);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarInstrumentoPorId(@PathVariable Long id) throws Exception {
        log.info("Solicitud para buscar instrumento por ID: {}", id);
        InstrumentoFinanciero instrumento = instrumentoFinancieroService.buscarInstrumentoPorId(id);
        log.info("Instrumento encontrado: {}", instrumento);
        return ResponseEntity.ok(instrumento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarInstrumento(@PathVariable Long id) throws Exception {
        log.info("Solicitud para eliminar instrumento con ID: {}", id);
        instrumentoFinancieroService.eliminarInstrumento(id);
        log.info("Instrumento con ID {} eliminado", id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarInstrumento(@PathVariable Long id, @RequestBody InstrumentoDTO nuevoInstrumentoDTO) throws Exception {
        log.info("Solicitud para editar instrumento con ID: {}", id);
        InstrumentoFinanciero instrumentoFinancieroActualizado = instrumentoFinancieroService.editarInstrumento(id, nuevoInstrumentoDTO);
        log.info("Instrumento con ID {} actualizado exitosamente", id);
        return ResponseEntity.ok(instrumentoFinancieroActualizado);
    }

    @PostMapping("/suscribir/{nombreInstrumento}/{nombreInversor}")
    public ResponseEntity<?> suscribirInversor(@PathVariable String nombreInstrumento, @PathVariable String nombreInversor) throws Exception {
        log.info("Solicitud para suscribir inversor {} al instrumento {}", nombreInversor, nombreInstrumento);
        try {
            instrumentoFinancieroService.suscribirInversor(nombreInstrumento, nombreInversor);
            log.info("Inversor {} suscrito al instrumento {}", nombreInversor, nombreInstrumento);
            return ResponseEntity.ok("Inversor suscrito al instrumento correctamente.");
        } catch (IllegalArgumentException e) {
            log.error("Error al suscribir el inversor: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

}
