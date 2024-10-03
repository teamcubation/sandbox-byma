package springbootMigracion.java.com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import springbootMigracion.java.com.example.demo.dto.InversorDTO;
import springbootMigracion.java.com.example.demo.model.Inversor;

import java.util.List;

@Tag(name = "Inversor", description = "Controlador de Inversores")
public interface IInversorApi {

    @Operation(
            summary = "Agregar inversor",
            description = "Agrega el inversor a la lista de de inversores"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inversor agregado correctamente"),
            @ApiResponse(responseCode = "409", description = "Inversor duplicado")
    })
    ResponseEntity<?> registrarInversor(@RequestBody InversorDTO inversoroDTO) throws Exception;

    @Operation(
            summary = "Listar todos los inversores o filtrar por nombre",
            description = "En caso de que se ingrese como parametro un nombre, lista todos " +
                    "los inversores que coinciden con el nombre ingresado, de lo contrario, " +
                    "lista todos los inversores"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inversores listados correctamente")
    })
    ResponseEntity<List<Inversor>> obtenerTodosLosInversoresOFiltrarPorNombre(@RequestParam(value = "nombre", required = false) String nombre);

    @Operation(
            summary = "Obtener inversor por id",
            description = "Obtiene el inversor que coincide con id ingresado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inversor obtenido correctamente"),
            @ApiResponse(responseCode = "404", description = "Inversor no encontrado")
    })
    ResponseEntity<?> buscarInversorPorId(@PathVariable Long id) throws Exception;

    @Operation(
            summary = "Eliminar inversor por id",
            description = "Elimina el inversor que coincide con id ingresado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Inversor eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Inversor no encontrado")
    })
    ResponseEntity<?> eliminarInversor(@PathVariable Long id) throws Exception;

    @Operation(
            summary = "Actualizar inversor",
            description = "Actualiza el inversor que coincide con id ingresado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inversor actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Inversor no encontrado")
    })
    ResponseEntity<?> editarInversor(@PathVariable Long id, @RequestBody InversorDTO nuevoInversorDTO) throws Exception;




}
