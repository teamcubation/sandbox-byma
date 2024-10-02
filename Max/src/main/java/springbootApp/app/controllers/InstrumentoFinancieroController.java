package  springbootApp.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootApp.app.apis.InstrumentoFinancieroApi;
import  springbootApp.app.controllers.mappers.InstrumentoMapper;
import springbootApp.app.controllers.DTOs.InstrumentoDTO;
import  springbootApp.app.models.InstrumentoFinanciero;
import  springbootApp.app.services.InstrumentoFinancieroService;
import springbootApp.app.services.interfaces.IInstrumentoFinancieroService;

@RestController
@RequestMapping("/instrumento-financiero")
public class InstrumentoFinancieroController implements InstrumentoFinancieroApi {

    private static final Logger log = LoggerFactory.getLogger(InstrumentoFinancieroController.class);
    @Autowired
    private IInstrumentoFinancieroService instrumentoFinancieroService;


    @PostMapping("/")
    public ResponseEntity<?> registrar(@RequestBody InstrumentoDTO instrumentoDTO){
            log.info("registrando instrumento...");
            InstrumentoFinanciero instrumento = InstrumentoMapper.instrumentoDTOToInstrumento(instrumentoDTO);
            if (instrumento == null)
                throw new NullPointerException("Error. El tipo es incorrecto");
            instrumentoFinancieroService.registrarInstrumentoFinanciero(instrumento);
            log.info("nuevo instrumento creado: " + instrumentoDTO);
            return new ResponseEntity<InstrumentoDTO>(instrumentoDTO, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<?> obtenerTodos(){
            log.info("obteniendo todos los instrumentos");
            return ResponseEntity.ok(instrumentoFinancieroService.consultarTodosLosInstrumentos());
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> obtenerPorNombre(@PathVariable String nombre){
            log.info("obteniendo instrumento por nombre...");
            return ResponseEntity.ok(instrumentoFinancieroService.buscarInstrumentoPorNombre(nombre));
        }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorID(@PathVariable Long id){
        log.info("obteniendo instrumento por ID...");
        return ResponseEntity.ok(instrumentoFinancieroService.buscarInstrumentoPorID(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
            log.info("eliminando instrumento: " + id);
            instrumentoFinancieroService.eliminarInstrumento(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody InstrumentoDTO instrumentoDTO) {
            log.info("actualizando instrumento...");
            InstrumentoFinanciero instrumento = InstrumentoMapper.instrumentoDTOToInstrumento(instrumentoDTO);
            if (instrumento == null)
                throw new NullPointerException("Error. El tipo es incorrecto");
            return ResponseEntity.ok(instrumentoFinancieroService.actualizarInstrumento(id, instrumento));
    }
}