package springApp.java.com.example.gestoralyc.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import springApp.java.com.example.gestoralyc.dto.BonoDTO;

import java.util.List;

public interface IBonoController {

    @Operation(summary = "Agregar un nuevo bono")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bono creado", content = @Content(schema = @Schema(implementation = BonoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos de bono inválidos", content = @Content),
            @ApiResponse(responseCode = "409", description = "Bono duplicado", content = @Content)
    })
    public ResponseEntity<BonoDTO> agregarBono(@RequestBody BonoDTO bonoDTO);

    @Operation(summary = "Obtener la lista de todos los bonos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de bonos obtenida", content = @Content(schema = @Schema(implementation = List.class)))
    })
    public ResponseEntity<List<BonoDTO>> obtenerBonos();

    @Operation(summary = "Obtener un bono por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bono encontrado", content = @Content(schema = @Schema(implementation = BonoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Bono no encontrado", content = @Content)
    })
    public ResponseEntity<BonoDTO> obtenerBonoPorId(@PathVariable Long id);

    @Operation(summary = "Eliminar un bono por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Bono eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Bono no encontrado", content = @Content)
    })
    public ResponseEntity<Void> eliminarBonoPorId(@PathVariable Long id);

    @Operation(summary = "Editar un bono existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bono actualizado exitosamente", content = @Content(schema = @Schema(implementation = BonoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos de bono inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Bono no encontrado", content = @Content),
            @ApiResponse(responseCode = "409", description = "Bono duplicado", content = @Content)
    })
    public ResponseEntity<BonoDTO> actualizarBono(@RequestBody BonoDTO bonoDTO);
}
