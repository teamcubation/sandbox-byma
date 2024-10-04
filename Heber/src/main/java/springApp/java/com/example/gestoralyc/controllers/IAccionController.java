package springApp.java.com.example.gestoralyc.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import springApp.java.com.example.gestoralyc.constantes.ApiRespuestaConstantes;
import springApp.java.com.example.gestoralyc.dto.AccionDTO;

import java.util.List;

public interface IAccionController {

    @Operation(summary = "Agregar una nueva acción")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_201,
                    description = ApiRespuestaConstantes.DESC_ACCION_CREADA,
                    content = @Content(schema = @Schema(implementation = AccionDTO.class))),
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_400,
                    description = ApiRespuestaConstantes.DESC_DATOS_INVALIDOS,
                    content = @Content),
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_409,
                    description = ApiRespuestaConstantes.DESC_ACCION_DUPLICADA,
                    content = @Content)
    })
    ResponseEntity<AccionDTO> agregarAccion(@RequestBody AccionDTO accionDTO);

    @Operation(summary = "Obtener la lista de todas las acciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_200,
                    description = ApiRespuestaConstantes.DESC_LISTA_ACCIONES_OBTENIDA,
                    content = @Content(schema = @Schema(implementation = List.class)))
    })
    ResponseEntity<List<AccionDTO>> obtenerAcciones();

    @Operation(summary = "Obtener una acción por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_200,
                    description = ApiRespuestaConstantes.DESC_ACCION_ENCONTRADA,
                    content = @Content(schema = @Schema(implementation = AccionDTO.class))),
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_404,
                    description = ApiRespuestaConstantes.DESC_ACCION_NO_ENCONTRADA,
                    content = @Content)
    })
    ResponseEntity<AccionDTO> obtenerAccionPorId(@PathVariable Long id);

    @Operation(summary = "Eliminar una acción por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_204,
                    description = ApiRespuestaConstantes.DESC_ACCION_ELIMINADA),
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_404,
                    description = ApiRespuestaConstantes.DESC_ACCION_NO_ENCONTRADA,
                    content = @Content)
    })
    ResponseEntity<Void> eliminarAccionPorId(@PathVariable Long id);

    @Operation(summary = "Editar una acción existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_200,
                    description = ApiRespuestaConstantes.DESC_ACCION_ACTUALIZADA,
                    content = @Content(schema = @Schema(implementation = AccionDTO.class))),
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_400,
                    description = ApiRespuestaConstantes.DESC_DATOS_INVALIDOS,
                    content = @Content),
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_404,
                    description = ApiRespuestaConstantes.DESC_ACCION_NO_ENCONTRADA,
                    content = @Content),
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_409,
                    description = ApiRespuestaConstantes.DESC_ACCION_DUPLICADA,
                    content = @Content)
    })
    ResponseEntity<AccionDTO> actualizarAccion(@RequestBody AccionDTO accionDTO);

    @Operation(summary = "Actualizar parcialmente una acción por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_200,
                    description = ApiRespuestaConstantes.DESC_ACCION_ACTUALIZADA_PARCIAL,
                    content = @Content(schema = @Schema(implementation = AccionDTO.class))),
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_400,
                    description = ApiRespuestaConstantes.DESC_DATOS_INVALIDOS,
                    content = @Content),
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_404,
                    description = ApiRespuestaConstantes.DESC_ACCION_NO_ENCONTRADA,
                    content = @Content)
    })
    ResponseEntity<AccionDTO> actualizarParcialmenteAccion(@PathVariable Long id, @RequestBody AccionDTO accionDTO);
}
