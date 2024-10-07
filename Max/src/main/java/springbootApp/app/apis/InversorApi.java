package springbootApp.app.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import springbootApp.app.controllers.DTOs.InversorDTO;

@Tag(name = "Inversor")
public interface InversorApi {

    @Operation(summary = "Registrar un inversor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inversor creado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<?> registrar(@RequestBody InversorDTO inversorDTO);

    @Operation(summary = "Obtener todos los inversores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inversores encontrados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<?> obtenerTodos();

    @Operation(summary = "Obtener un inversor por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inversor encontrado"),
            @ApiResponse(responseCode = "404", description = "Error: Inversor no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<?> obtenerPorID(Long id);

    @Operation(summary = "Actualizar un inversor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inversor actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Error: Inversor no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<?> actualizar(Long id, InversorDTO inversorDTO);

    @Operation(summary = "Eliminar un inversor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Inversor eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Error: Inversor no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<?> eliminar(Long id);

    @Operation(summary = "Obtener los instrumentos de un inversor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inversor encontrado"),
            @ApiResponse(responseCode = "404", description = "Error: Inversor no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<?> obtenerInstrumentosDeInversor(Long id);

    @Operation(summary = "Suscribir un inversor a un instrumento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inversor suscrito exitosamente"),
            @ApiResponse(responseCode = "404", description = "Error: Inversor o instrumento no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<?>suscribir(Long id, Long idInstrumento);

    @Operation(summary = "Desuscribir un inversor de un instrumento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inversor desuscrito exitosamente"),
            @ApiResponse(responseCode = "404", description = "Error: Inversor o instrumento no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<?>desuscribir(Long id, Long idInstrumento);
}