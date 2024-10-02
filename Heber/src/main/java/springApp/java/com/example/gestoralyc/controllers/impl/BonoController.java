package springApp.java.com.example.gestoralyc.controllers.impl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springApp.java.com.example.gestoralyc.dto.BonoDTO;
import springApp.java.com.example.gestoralyc.mappers.BonoMapper;
import springApp.java.com.example.gestoralyc.models.BonoModel;
import springApp.java.com.example.gestoralyc.services.BonoService;
import springApp.java.com.example.gestoralyc.utils.GeneradorCurl;

import java.util.List;

@RestController
@RequestMapping("/api/bonos")
@Slf4j
@AllArgsConstructor
public class BonoController {
    private final BonoService bonoService;

    @Operation(summary = "Agregar un nuevo bono")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bono creado", content = @Content(schema = @Schema(implementation = BonoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos de bono inválidos", content = @Content),
            @ApiResponse(responseCode = "409", description = "Bono duplicado", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<BonoDTO> agregarBono(@RequestBody BonoDTO bonoDTO) {
        log.info("Bono recibido en formato cURL: \n{}", GeneradorCurl.generarCurlBono(bonoDTO));
        BonoDTO bonoCreadoDTO = BonoMapper
                .mapToDTO(bonoService.agregarBono((BonoModel) BonoMapper.mapToModel(bonoDTO)));
        log.info("Bono creado en formato cURL: \n{}", GeneradorCurl.generarCurlBono(bonoCreadoDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(bonoCreadoDTO);
    }

    @Operation(summary = "Obtener la lista de todos los bonos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de bonos obtenida", content = @Content(schema = @Schema(implementation = List.class)))
    })
    @GetMapping("/")
    public ResponseEntity<List<BonoDTO>> obtenerBonos() {
        log.info("Obteniendo instrumentos");
        List<BonoModel> bonosList = bonoService.obtenerBonos();
        List<BonoDTO> bonosDTOList = bonosList.stream()
                .map(BonoMapper::mapToDTO)
                .toList();

        log.info("Instrumentos obtenidos: {}", bonosList);
        return ResponseEntity.ok(bonosDTOList);
    }

    @Operation(summary = "Obtener un bono por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bono encontrado", content = @Content(schema = @Schema(implementation = BonoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Bono no encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<BonoDTO> obtenerBonoPorId(@PathVariable Long id) {
        log.info("Obteniendo instrumento con id: {}", id);
        BonoModel bono = bonoService.obtenerBonoPorId(id);
        BonoDTO bonoDTO = BonoMapper.mapToDTO(bono);
        log.info("Instrumento obtenido: {}", bonoDTO);
        return ResponseEntity.ok(bonoDTO);
    }

    @Operation(summary = "Eliminar un bono por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Bono eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Bono no encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBonoPorId(@PathVariable Long id) {
        log.info("Eliminando bono con id: {}", id);
        bonoService.eliminarBonoPorId(id);
        log.info("Bono eliminado con id: {}", id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Editar un bono existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bono actualizado exitosamente", content = @Content(schema = @Schema(implementation = BonoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos de bono inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Bono no encontrado", content = @Content),
            @ApiResponse(responseCode = "409", description = "Bono duplicado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<BonoDTO> editarBono(@PathVariable Long id, @RequestBody BonoDTO bonoDTO) {
        log.info("Editando bono con id: {}", id);
        BonoModel bonoModel = BonoMapper.mapToModel(bonoDTO);
        BonoModel bonoEditado = bonoService.editarBono(id, bonoModel);
        BonoDTO bonoEditadoDTO = BonoMapper.mapToDTO(bonoEditado);
        log.info("Bono editado: {}", bonoEditadoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(bonoEditadoDTO);
    }
}
