package com.example.proyectoSpringBoot.controller;

import com.example.proyectoSpringBoot.dto.InstrumentoFinancieroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Instrumentos Financieros", description = "Instrumentos Financieros API")
public interface InstrumentoFinancieroApi {

    @Operation(
            summary = "Consulta todos los instrumentos finacieros",
            description = "Realiza una consulta de todos los instrumentos financieros con sus datos (nombre, precio, tipo[Bono= 1, Accion= 2])")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa")
    })
    List<InstrumentoFinancieroDTO> consultarTodos();

    @Operation(
            summary = "Consulta por un instrumentos finacieros",
            description = "Realiza una consulta por id de un instrumento financiero")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "404", description = "Elemento no encontrado")
    })
    ResponseEntity<?> consultar(@PathVariable("id") Long id);

    @Operation(
            summary = "Registra un instrumento financiero",
            description = "Registra el instrumento financiero pasado en el cuerpo de la peticion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Creacion exitosa"),
            @ApiResponse(responseCode = "409", description = "Elemento duplicado")
    })
    ResponseEntity<?> registrar (@RequestBody InstrumentoFinancieroDTO instrumentoDTO);

    @Operation(
            summary = "Elimina un instrumento financiero",
            description = "Elimina un instrumento financiero por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "404", description = "Elemento no encontrado")
    })
    ResponseEntity<?> eliminar(@PathVariable("id") Long id);

    @Operation(
            summary = "Edita un instrumento financiero",
            description = "Realiza la edicion de un instrumento financiero por id segun los datos pasados en el cuerpo de la peticion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "404", description = "Elemento no encontrado")
    })
    ResponseEntity<?> editar(@PathVariable("id") Long id, @RequestBody InstrumentoFinancieroDTO instrumentoDTO);
}
