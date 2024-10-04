package com.example.teamcubation.controller;

import com.example.teamcubation.exceptions.InstrumentoDuplicadoException;
import com.example.teamcubation.exceptions.InstrumentoNoEncontradoException;
import com.example.teamcubation.exceptions.ModeloInvalidoException;
import com.example.teamcubation.model.InstrumentoDTO.BonoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;


@Tag(name = BonoController.TAG_BONOS, description = BonoController.TAG_BONOS_DESC)
public interface BonoController {


    // Constantes para las descripciones
    String TAG_BONOS = "Bonos";
    String TAG_BONOS_DESC = "Operaciones relacionadas con los bonos financieros";


    String CREAR_BONO_SUMMARY = "Crear un nuevo Bono";
    String CREAR_BONO_DESC = "Este endpoint permite crear un nuevo bono en el sistema.";

    String LISTAR_BONOS_SUMMARY = "Listar todos los Bonos";
    String LISTAR_BONOS_DESC = "Este endpoint devuelve una lista de todos los bonos registrados en el sistema.";

    String ACTUALIZAR_BONO_SUMMARY = "Actualizar un Bono existente";
    String ACTUALIZAR_BONO_DESC = "Este endpoint permite actualizar la información de un bono existente dado su ID.";

    String ELIMINAR_BONO_SUMMARY = "Eliminar un Bono";
    String ELIMINAR_BONO_DESC = "Este endpoint permite eliminar un bono del sistema dado su ID.";


    @Operation(
            summary = CREAR_BONO_SUMMARY,
            description = CREAR_BONO_DESC
    )
    ResponseEntity<?> createBono(BonoDTO nuevoBono) throws ModeloInvalidoException, InstrumentoDuplicadoException;

    @Operation(
            summary = LISTAR_BONOS_SUMMARY,
            description = LISTAR_BONOS_DESC
    )
    ResponseEntity<?> listarBonos();

    @Operation(
            summary = ACTUALIZAR_BONO_SUMMARY,
            description = ACTUALIZAR_BONO_DESC
    )
    ResponseEntity<?> updateBono(Long id, BonoDTO bonoDTO) throws InstrumentoNoEncontradoException, ModeloInvalidoException, InstrumentoDuplicadoException;

    @Operation(
            summary = ELIMINAR_BONO_SUMMARY,
            description = ELIMINAR_BONO_DESC
    )
    ResponseEntity<?> deleteBono(long id) throws InstrumentoNoEncontradoException;
}
