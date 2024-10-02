package springbootApp.app.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import springbootApp.app.controllers.DTOs.InstrumentoDTO;

@Tag(name = "Instrumento Financiero")
public interface InstrumentoFinancieroApi {

    @Operation(summary = "Registrar un instrumento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Instrumento creado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<?> registrar(@RequestBody InstrumentoDTO instrumentoDTO);

    @Operation(summary = "Obtener todos los instrumentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instrumentos encontrados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<?> obtenerTodos();

    @Operation(summary = "Obtener un instrumento por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instrumento encontrado"),
            @ApiResponse(responseCode = "404", description = "Error: Instrumento no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<?> obtenerPorID(Long id);

    @Operation(summary = "Actualizar un instrumento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instrumento actualizado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<?> actualizar(Long id, InstrumentoDTO instrumentoDTO);

    @Operation(summary = "Eliminar un instrumento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Instrumento eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Error: Instrumento no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<?> eliminar(Long id);

    @Operation(summary = "Obtener un instrumento por nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instrumento encontrado"),
            @ApiResponse(responseCode = "404", description = "Error: Instrumento no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<?> obtenerPorNombre(String nombre);
}
