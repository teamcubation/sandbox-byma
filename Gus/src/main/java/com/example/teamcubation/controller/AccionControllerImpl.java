package com.example.teamcubation.controller;

import com.example.teamcubation.exceptions.InstrumentoDuplicadoException;
import com.example.teamcubation.exceptions.InstrumentoNoEncontradoException;
import com.example.teamcubation.exceptions.ModeloInvalidoException;
import com.example.teamcubation.model.Accion;
import com.example.teamcubation.model.InstrumentoDTO.AccionDTO;
import com.example.teamcubation.service.AccionService;
import com.example.teamcubation.util.mappers.AccionMapper;
import com.example.teamcubation.util.validaciones.ValidatorAccionDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instrumentos-financieros/accion")
@Slf4j
public class AccionControllerImpl implements AccionController {

    private final AccionService accionService;

    @Autowired
    public AccionControllerImpl(AccionService accionService) {
        this.accionService = accionService;
    }


    //create

    /**
     * Crea un nuevo instrumento accion y lo agrega a la lista de instrumentos financieros.
     *
     * @param nuevaAccion El instrumento accion a crear
     * @return El instrumento accion creado
     * @throws InstrumentoDuplicadoException Si el instrumento ya existe
     * @throws ModeloInvalidoException       Si el modelo de instrumento no es valido
     */
    @PostMapping
    @Override
    public ResponseEntity<?> createAccion(@RequestBody @Valid AccionDTO nuevaAccion) throws ModeloInvalidoException, InstrumentoDuplicadoException {
        log.info("Instrumento a crear: " + nuevaAccion.toString());

//        ValidatorAccionDTO.validarAccionDTO(nuevaAccion);

        Accion accionCreada = this.accionService.createAccion(AccionMapper.toAccion(nuevaAccion));

        log.info("Instrumento creado: " + accionCreada);


        return ResponseEntity.status(HttpStatus.CREATED).body(accionCreada);

    }


    //Read

    /**
     * Devuelve una lista de todos los instrumentos financieros de tipo accion.
     *
     * @return La lista de instrumentos financieros de tipo accion
     */
    @GetMapping
    @Override
    public ResponseEntity<?> listarAcciones() {

        List<AccionDTO> acciones = accionService
                .getAllAcciones()
                .stream()
                .map(AccionMapper::toAccionDTO)
                .toList();

        log.info(acciones.toString());
        return ResponseEntity.status(HttpStatus.OK).body(acciones);
    }

    //update

    /**
     * Actualiza un instrumento accion existente.
     *
     * @param id        El id del instrumento accion a actualizar
     * @param accionDTO El instrumento accion con los nuevos valores
     * @return El instrumento accion actualizado
     * @throws InstrumentoNoEncontradoException Si no se encuentra el instrumento accion
     * @throws ModeloInvalidoException          Si el instrumento accion no es valido
     * @throws InstrumentoDuplicadoException    Si el nombre del instrumento accion ya existe
     */
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<?> updateAccion(@PathVariable Long id, @RequestBody @Valid AccionDTO accionDTO) throws InstrumentoNoEncontradoException, ModeloInvalidoException, InstrumentoDuplicadoException {


        ValidatorAccionDTO.validarAccionDTO(accionDTO);

        log.info("PathVariable id=  " + id);
        log.info(accionDTO.toString());

        Accion accionPorActualizar = AccionMapper.toAccion(accionDTO);
        accionPorActualizar.setId(id);


        Accion accionActualizada = this.accionService.updateAccion(accionPorActualizar);

        log.info("Instrumento actualizado: " + accionActualizada.toString());
        return ResponseEntity.status(HttpStatus.OK).body(accionActualizada);

    }


    //
    //documentar

    /**
     * Elimina un instrumento accion existente.
     * <p>
     * El instrumento accion se busca por su id, si no se encuentra se lanza una excepcion
     * {@link InstrumentoNoEncontradoException}.
     * </p>
     * <p>
     * Si el instrumento accion se elimina correctamente, se devuelve un objeto
     * {@link ResponseEntity} con estado {@link HttpStatus#NO_CONTENT}.
     * </p>
     *
     * @param id El id del instrumento accion a eliminar
     * @return Un objeto {@link ResponseEntity} con estado {@link HttpStatus#NO_CONTENT}
     * @throws InstrumentoNoEncontradoException Si no se encuentra el instrumento accion
     */
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<?> deleteAccion(@PathVariable long id) throws InstrumentoNoEncontradoException {

        log.info("PathVariable id=  " + id);
        this.accionService.deleteAccion(id);

        log.info("Instrumento eliminado: " + id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
