package springbootMigracion.java.com.example.demo.controller;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootMigracion.java.com.example.demo.dto.InstrumentoDTO;
import springbootMigracion.java.com.example.demo.model.InstrumentoFinanciero;
import springbootMigracion.java.com.example.demo.service.IInstrumentoFinancieroService;
import springbootMigracion.java.com.example.demo.utils.logs.LogMessages;

import java.util.List;

@RestController
@RequestMapping("/api/instrumentos")
@Slf4j
public class InstrumentoFinancieroController implements IInstrumentoFinancieroApi {

    @Autowired
    private IInstrumentoFinancieroService instrumentoFinancieroService;

    @PostMapping()
    public ResponseEntity<?> registrarInstrumento(@RequestBody InstrumentoDTO instrumentoDTO) throws Exception {
        log.info(LogMessages.SOLICITUD_REGISTRAR_INSTRUMENTO.getMessage(), instrumentoDTO);
        InstrumentoFinanciero instrumento = instrumentoFinancieroService.registrarInstrumento(instrumentoDTO);
        log.info(LogMessages.INSTRUMENTO_AGREGADO.getMessage(), instrumento);
        return ResponseEntity.status(HttpStatus.CREATED).body(instrumento);
    }

    @GetMapping()
    public ResponseEntity<List<InstrumentoFinanciero>> obtenerTodosLosInstrumentosOFiltrarPorNombre(@RequestParam(value = "nombre", required = false) String nombre) {
        List<InstrumentoFinanciero> instrumentos;
        if (nombre != null && !nombre.isEmpty()) {
            instrumentos = instrumentoFinancieroService.listarInstrumentosPorNombre(nombre);
            log.info(LogMessages.INSTRUMENTOS_ENCONTRADOS_POR_NOMBRE.getMessage(), nombre, instrumentos.size());
        } else {
            instrumentos = instrumentoFinancieroService.listarTodosLosInstrumentos();
            log.info(LogMessages.TODOS_LOS_INSTRUMENTOS_ENCONTRADOS.getMessage(), instrumentos.size());
        }
        return ResponseEntity.ok(instrumentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarInstrumentoPorId(@PathVariable Long id) throws Exception {
        log.info(LogMessages.SOLICITUD_BUSCAR_INSTRUMENTO_POR_ID.getMessage(), id);
        InstrumentoFinanciero instrumento = instrumentoFinancieroService.buscarInstrumentoPorId(id);
        log.info(LogMessages.INSTRUMENTO_ENCONTRADO.getMessage(), instrumento);
        return ResponseEntity.ok(instrumento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarInstrumento(@PathVariable Long id) throws Exception {
        log.info(LogMessages.SOLICITUD_ELIMINAR_INSTRUMENTO.getMessage(), id);
        instrumentoFinancieroService.eliminarInstrumento(id);
        log.info(LogMessages.INSTRUMENTO_ELIMINADO.getMessage(), id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarInstrumento(@PathVariable Long id, @RequestBody InstrumentoDTO nuevoInstrumentoDTO) throws Exception {
        log.info(LogMessages.SOLICITUD_EDITAR_INSTRUMENTO.getMessage(), id);
        InstrumentoFinanciero instrumentoFinancieroActualizado = instrumentoFinancieroService.editarInstrumento(id, nuevoInstrumentoDTO);
        log.info(LogMessages.INSTRUMENTO_EDITADO.getMessage(), id);
        return ResponseEntity.ok(instrumentoFinancieroActualizado);
    }

    @PostMapping("/suscribir/{nombreInstrumento}/inversor/{nombreInversor}")
    public ResponseEntity<?> suscribirInversor(@PathVariable String nombreInstrumento, @PathVariable String nombreInversor) throws Exception {
        log.info(LogMessages.SOLICITUD_SUSCRIBIR_INVERSOR.getMessage(), nombreInversor, nombreInstrumento);
        try {
            instrumentoFinancieroService.suscribirInversor(nombreInstrumento, nombreInversor);
            log.info(LogMessages.INVERSOR_SUSCRITO.getMessage(), nombreInversor, nombreInstrumento);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            log.error(LogMessages.ERROR_SUSCRIBIR_INVERSOR.getMessage(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
