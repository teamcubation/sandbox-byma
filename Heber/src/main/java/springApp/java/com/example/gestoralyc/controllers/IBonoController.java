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
import springApp.java.com.example.gestoralyc.dto.BonoDTO;

import java.util.List;

public interface IBonoController {

    @Operation(summary = "Agregar un nuevo bono")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_201,
                    description = ApiRespuestaConstantes.DESC_BONO_CREADO,
                    content = @Content(schema = @Schema(implementation = BonoDTO.class))),
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_400,
                    description = ApiRespuestaConstantes.DESC_DATOS_BONO_INVALIDOS,
                    content = @Content),
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_409,
                    description = ApiRespuestaConstantes.DESC_BONO_DUPLICADO,
                    content = @Content)
    })
    public ResponseEntity<BonoDTO> agregarBono(@RequestBody BonoDTO bonoDTO);

    @Operation(summary = "Obtener la lista de todos los bonos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_200,
                    description = ApiRespuestaConstantes.DESC_LISTA_BONOS_OBTENIDA,
                    content = @Content(schema = @Schema(implementation = List.class)))
    })
    public ResponseEntity<List<BonoDTO>> obtenerBonos();

    @Operation(summary = "Obtener un bono por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_200,
                    description = ApiRespuestaConstantes.DESC_BONO_ENCONTRADO,
                    content = @Content(schema = @Schema(implementation = BonoDTO.class))),
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_404,
                    description = ApiRespuestaConstantes.DESC_BONO_NO_ENCONTRADO,
                    content = @Content)
    })
    public ResponseEntity<BonoDTO> obtenerBonoPorId(@PathVariable Long id);

    @Operation(summary = "Eliminar un bono por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_204,
                    description = ApiRespuestaConstantes.DESC_BONO_ELIMINADO),
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_404,
                    description = ApiRespuestaConstantes.DESC_BONO_NO_ENCONTRADO,
                    content = @Content)
    })
    public ResponseEntity<Void> eliminarBonoPorId(@PathVariable Long id);

    @Operation(summary = "Editar un bono existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_201,
                    description = ApiRespuestaConstantes.DESC_BONO_ACTUALIZADO,
                    content = @Content(schema = @Schema(implementation = BonoDTO.class))),
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_400,
                    description = ApiRespuestaConstantes.DESC_DATOS_BONO_INVALIDOS,
                    content = @Content),
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_404,
                    description = ApiRespuestaConstantes.DESC_BONO_NO_ENCONTRADO,
                    content = @Content),
            @ApiResponse(responseCode = ApiRespuestaConstantes.RESPONSE_CODE_409,
                    description = ApiRespuestaConstantes.DESC_BONO_DUPLICADO,
                    content = @Content)
    })
    public ResponseEntity<BonoDTO> actualizarBono(@RequestBody BonoDTO bonoDTO);
}
