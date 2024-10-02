package springApp.java.com.example.gestoralyc.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import springApp.java.com.example.gestoralyc.dto.AccionDTO;

import java.util.List;

public interface IAccionController {

    @Operation(summary = "Agregar una nueva acción")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Acción creada", content = @Content(schema = @Schema(implementation = AccionDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos de acción inválidos", content = @Content),
            @ApiResponse(responseCode = "409", description = "Acción duplicada", content = @Content)
    })
    public ResponseEntity<AccionDTO> agregarAccion(@RequestBody AccionDTO accionDTO);

    @Operation(summary = "Obtener la lista de todas las acciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de acciones obtenida", content = @Content(schema = @Schema(implementation = List.class)))
    })
    public ResponseEntity<List<AccionDTO>> obtenerInstrumentos();

    @Operation(summary = "Obtener una acción por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acción encontrada", content = @Content(schema = @Schema(implementation = AccionDTO.class))),
            @ApiResponse(responseCode = "404", description = "Acción no encontrada", content = @Content)
    })
    public ResponseEntity<AccionDTO> obtenerInstrumentoPorId(@PathVariable Long id);

    @Operation(summary = "Eliminar una acción por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Acción eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Acción no encontrada", content = @Content)
    })
    public ResponseEntity<Void> eliminarInstrumentoPorId(@PathVariable Long id);

    @Operation(summary = "Editar una acción existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Acción actualizada exitosamente", content = @Content(schema = @Schema(implementation = AccionDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos de acción inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Acción no encontrada", content = @Content),
            @ApiResponse(responseCode = "409", description = "Acción duplicada", content = @Content)
    })
    public ResponseEntity<AccionDTO> actualizarInstrumento(@RequestBody AccionDTO accionDTO);
}
