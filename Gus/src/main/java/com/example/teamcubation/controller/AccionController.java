package com.example.teamcubation.controller;

import com.example.teamcubation.exceptions.InstrumentoDuplicadoException;
import com.example.teamcubation.exceptions.InstrumentoNoEncontradoException;
import com.example.teamcubation.exceptions.ModeloInvalidoException;
import com.example.teamcubation.model.InstrumentoDTO.AccionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;


@Tag(name = AccionController.TAG_ACCIONES, description = "Operaciones relacionadas con las acciones financieras")
public interface AccionController {
    // Constantes
    String TAG_ACCIONES = "Acciones";
    String CREAR_ACCION_SUMMARY = "Crear una nueva acción";
    String CREAR_ACCION_DESC = "Crea una nueva acción y la almacena en el sistema.";
    String LISTAR_ACCIONES_SUMMARY = "Listar todas las acciones";
    String LISTAR_ACCIONES_DESC = "Obtiene una lista de todas las acciones disponibles en el sistema.";
    String ACTUALIZAR_ACCION_SUMMARY = "Actualizar una acción por ID";
    String ACTUALIZAR_ACCION_DESC = "Actualiza los detalles de una acción existente.";
    String ELIMINAR_ACCION_SUMMARY = "Eliminar una acción por ID";
    String ELIMINAR_ACCION_DESC = "Elimina una acción existente del sistema.";
    String DATOS_NUEVA_ACCION_DESC = "Datos de la nueva acción";
    String DATOS_ACTUALIZADOS_DESC = "Datos actualizados de la acción";

    // Media types
    String MEDIA_TYPE_JSON = "application/json";


    @Operation(
            summary = CREAR_ACCION_SUMMARY,
            description = CREAR_ACCION_DESC
    )
    ResponseEntity<?> createAccion(AccionDTO nuevaAccion) throws ModeloInvalidoException, InstrumentoDuplicadoException;


    @Operation(
            summary = LISTAR_ACCIONES_SUMMARY,
            description = LISTAR_ACCIONES_DESC
    )
    ResponseEntity<?> listarAcciones();

    @Operation(
            summary = ACTUALIZAR_ACCION_SUMMARY,
            description = ACTUALIZAR_ACCION_DESC
    )
    ResponseEntity<?> updateAccion(Long id, AccionDTO accionDTO) throws InstrumentoNoEncontradoException, ModeloInvalidoException, InstrumentoDuplicadoException;


    @Operation(
            summary = ELIMINAR_ACCION_SUMMARY,
            description = ELIMINAR_ACCION_DESC
    )
    ResponseEntity<?> deleteAccion(long id) throws InstrumentoNoEncontradoException;
}
