package com.example.project.controller;

import com.example.project.controller.dto.EditarInstrumentoDTO;
import com.example.project.controller.dto.InstrumentoFinancieroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "InstrumentoFinanciero", description = " InstrumentoFinanciero Api")
public interface InstrumentoFinancieroController {

    @Operation(summary = "Obtener instrumentos financieros", description = "Obtener todos instrumentos financieros que han sido guardados en la base de datos.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operacion exitosa")})
    ResponseEntity<?> consultarTodosLosInstrumentosConocidos() ;

    @Operation(summary = "Obtener instrumento financiero por nombre", description = "Obtener todos instrumentos financieros que han sido guardados en la base de datos.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operacion exitosa")})
    ResponseEntity<?> consultarUnInstrumentoFinanciero(@PathVariable String nombre);
    // TODO: que responda un 404 si no lo encuentra

    @Operation(summary = "Crear un instrumento financiero", description = "Crear un instrumento financiero y persistirlo en la base de datos.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Instrumento creado"),
    @ApiResponse(responseCode = "404", description = "Instrumento no creado, el tipo ingresado no existe"),
    @ApiResponse(responseCode = "409", description = "Instrumento no creado, ya fue registrado anteriormente")})
    ResponseEntity<?> crearInstrumento(@RequestBody InstrumentoFinancieroDTO instrumentoFinancieroDTO) throws Exception;

    @Operation(summary = "Eliminar instrumento financiero por nombre", description = "Eliminar instrumento financiero previamente guardado en la base de datos.")
    @ApiResponses(value = {@ApiResponse(responseCode = "404", description = "Instrumento no eliminado, no existe en el sistema"),
    @ApiResponse(responseCode = "204", description = "Instrumento eliminado de manera exitosa")})
    ResponseEntity<?> deleteInstrumento(@PathVariable String nombre) throws Exception;

    @Operation(summary = "Editar instrumento financiero por nombre", description = "Editar instrumento financiero por nombre que previamente fue guardado en la base de datos. Es posible modificar los atributos nombre, precio y fecha de emision")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Instrumento editado exitosamente"),
    @ApiResponse(responseCode = "404", description = "El instrumento financiero no fue editado, no existe en el sistema")})
    ResponseEntity<?> editarInstrumento(@RequestBody EditarInstrumentoDTO editarInstrumentoDTO, @PathVariable String nombre) throws Exception;
}
