package springBootProject.java.com.example.proyectoSpringBoot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springBootProject.java.com.example.proyectoSpringBoot.dto.InstrumentoFinancieroDTO;
import springBootProject.java.com.example.proyectoSpringBoot.model.InstrumentoFinanciero;
import springBootProject.java.com.example.proyectoSpringBoot.service.serviceImpl.InstrumentoFinancieroServiceImpl;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/instrumentos-financieros")
public class InstrumentoFinancieroController {

    @Autowired
    private InstrumentoFinancieroServiceImpl instrumentoFinancieroService;

    @RequestMapping("")
    private List<InstrumentoFinanciero> consultarTodos() {
        log.info("Consultando instrumentos financieros");
        List<InstrumentoFinanciero> instrumentosFinancieros = this.instrumentoFinancieroService.consultarTodos();
        log.info("Instrumentos financieros consultados: {}", instrumentosFinancieros.size());
        return instrumentosFinancieros;
    }

    @RequestMapping("/{nombre}")
    private ResponseEntity<?> consultar(@PathVariable("nombre") String nombre) throws Exception {
        return ResponseEntity.ok(this.instrumentoFinancieroService.consultar(nombre));
    }

    @PostMapping("")
    private ResponseEntity<?> registrar (@RequestBody InstrumentoFinancieroDTO instrumentoDTO) throws Exception {
        log.info("registrando instrumento financiero");
        InstrumentoFinanciero instrumentoFinancieroNuevo = this.instrumentoFinancieroService.registrar(instrumentoDTO);
        log.info("instrumento financiero registrado");
        return ResponseEntity.status(HttpStatus.CREATED).body(instrumentoFinancieroNuevo);
    }

    @DeleteMapping("/{nombre}")
    private ResponseEntity<?> eliminar(@PathVariable("nombre") String nombre) throws Exception {
        this.instrumentoFinancieroService.eliminar(nombre);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{nombre}")
    private ResponseEntity<?> editar(@PathVariable("nombre") String instrumentoAEditar, @RequestBody InstrumentoFinancieroDTO instrumentoDTO) throws Exception {
        InstrumentoFinanciero instrumentoFinancieroEditado = this.instrumentoFinancieroService.editar(instrumentoAEditar, instrumentoDTO);
        return ResponseEntity.ok(instrumentoFinancieroEditado);
    }
}
