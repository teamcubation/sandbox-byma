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
import springbootApp.app.exceptions.TipoInvalidoException;
import  springbootApp.app.models.InstrumentoFinanciero;
import  springbootApp.app.services.InstrumentoFinancieroService;
import springbootApp.app.services.interfaces.IInstrumentoFinancieroService;

import static springbootApp.app.models.InstrumentoFactory.TIPO_INVALIDO;

@RestController
@RequestMapping("/instrumento-financiero")
public class InstrumentoFinancieroController implements InstrumentoFinancieroApi {

    private static final Logger log = LoggerFactory.getLogger(InstrumentoFinancieroController.class);
    public static final String REGISTRANDO_INSTRUMENTO = "registrando instrumento...";
    public static final String INSTRUMENTO_CREADO = "nuevo instrumento creado: ";
    public static final String OBTENIENDO_TODOS_LOS_INSTRUMENTOS = "obteniendo todos los instrumentos";
    public static final String INSTRUMENTO_POR_NOMBRE = "obteniendo instrumento por nombre...";
    public static final String INSTRUMENTO_POR_ID = "obteniendo instrumento por ID...";
    public static final String ELIMINANDO_INSTRUMENTO = "eliminando instrumento: ";
    public static final String ACTUALIZANDO_INSTRUMENTO = "actualizando instrumento...";
    @Autowired
    private IInstrumentoFinancieroService instrumentoFinancieroService;


    @PostMapping("/")
    public ResponseEntity<?> registrar(@RequestBody InstrumentoDTO instrumentoDTO){
            log.info(REGISTRANDO_INSTRUMENTO);
            InstrumentoFinanciero instrumento = InstrumentoMapper.instrumentoDTOToInstrumento(instrumentoDTO);
            if (instrumento == null)
                throw new TipoInvalidoException(TIPO_INVALIDO);
            instrumentoFinancieroService.registrarInstrumentoFinanciero(instrumento);
            log.info(INSTRUMENTO_CREADO + instrumentoDTO);
            return new ResponseEntity<InstrumentoDTO>(instrumentoDTO, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<?> obtenerTodos(){
            log.info(OBTENIENDO_TODOS_LOS_INSTRUMENTOS);
            return ResponseEntity.ok(instrumentoFinancieroService.consultarTodosLosInstrumentos());
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> obtenerPorNombre(@PathVariable String nombre){
            log.info(INSTRUMENTO_POR_NOMBRE);
            return ResponseEntity.ok(instrumentoFinancieroService.buscarInstrumentoPorNombre(nombre));
        }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorID(@PathVariable Long id){
        log.info(INSTRUMENTO_POR_ID);
        return ResponseEntity.ok(instrumentoFinancieroService.buscarInstrumentoPorID(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
            log.info(ELIMINANDO_INSTRUMENTO + id);
            instrumentoFinancieroService.eliminarInstrumento(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody InstrumentoDTO instrumentoDTO) {
            log.info(ACTUALIZANDO_INSTRUMENTO);
            InstrumentoFinanciero instrumento = InstrumentoMapper.instrumentoDTOToInstrumento(instrumentoDTO);
            if (instrumento == null)
                throw new TipoInvalidoException(TIPO_INVALIDO);
            return ResponseEntity.ok(instrumentoFinancieroService.actualizarInstrumento(id, instrumento));
    }
}