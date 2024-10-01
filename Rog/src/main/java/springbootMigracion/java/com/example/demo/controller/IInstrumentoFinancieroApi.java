package springbootMigracion.java.com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import springbootMigracion.java.com.example.demo.dto.InstrumentoDTO;
import springbootMigracion.java.com.example.demo.model.InstrumentoFinanciero;

import java.util.List;

@Tag(name = "Instrumento Financiero", description = "Controlador de Instrumento Financiero")
public interface IInstrumentoFinancieroApi {
    @Operation(
            summary = "Agregar instrumento financiero",
            description = "Agrega el instrumento financiero a la lista de de instrumentos financieros"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Instrumento agregado correctamente"),
            @ApiResponse(responseCode = "409", description = "Instrumento duplicado")
    })
    ResponseEntity<?> registrarInstrumento(@RequestBody InstrumentoDTO instrumentoDTO) throws Exception;

    @Operation(
            summary = "Listar instrumentos financieros",
            description = "Lista todos los instrumentos financieros"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instrumentos listados correctamente")
    })
    ResponseEntity<List<InstrumentoFinanciero>> listarInstrumentos();

    @Operation(
            summary = "Listar instrumentos financieros por nombre",
            description = "Lista todos los instrumentos financieros que coinciden con el nombre ingresado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instrumentos listados correctamente")
    })
    ResponseEntity<List<InstrumentoFinanciero>> listarInstrumentosPorNombre(@PathVariable String nombre);

    @Operation(
            summary = "Obtener instrumento financiero por id",
            description = "Obtiene el instrumento financiero que coincide con id ingresado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instrumento obtenido correctamente"),
            @ApiResponse(responseCode = "404", description = "Instrumento no encontrado")
    })
    ResponseEntity<?> buscarInstrumentoPorId(@PathVariable Long id) throws Exception;

    @Operation(
            summary = "Eliminar instrumento financiero por id",
            description = "Elimina el instrumento financiero que coincide con id ingresado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Instrumento eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Instrumento no encontrado")
    })
    ResponseEntity<?> eliminarInstrumento(@PathVariable Long id) throws Exception;

    @Operation(
            summary = "Actualizar instrumento financiero",
            description = "Actualiza el instrumento financiero que coincide con id ingresado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instrumento actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Instrumento no encontrado")
    })
    ResponseEntity<?> editarInstrumento(@PathVariable Long id, @RequestBody InstrumentoDTO nuevoInstrumentoDTO) throws Exception;

    @Operation(
            summary = "Suscribir inversor",
            description = "Suscribe un inversor al instrumento financiero que coincide con nombre ingresado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instrumento suscrito correctamente"),
            @ApiResponse(responseCode = "404", description = "Instrumento no encontrado"),
            @ApiResponse(responseCode = "404", description = "Inversor no encontrado")
    })
    ResponseEntity<?> suscribirInversor(@PathVariable String nombreInstrumento, @PathVariable String nombreInversor) throws Exception;


}
