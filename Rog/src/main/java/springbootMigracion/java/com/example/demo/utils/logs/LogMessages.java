package springbootMigracion.java.com.example.demo.utils.logs;

import lombok.Getter;

@Getter
public enum LogMessages {

    REGISTRANDO_INSTRUMENTO("Registrando instrumento: {}"),
    INSTRUMENTO_DUPLICADO("Instrumento duplicado: {}"),
    INSTRUMENTO_REGISTRADO("Instrumento registrado exitosamente: {}"),
    BUSCANDO_INSTRUMENTO_POR_ID("Buscando instrumento por ID: {}"),
    INSTRUMENTO_ENCONTRADO("Instrumento encontrado: {}"),
    INSTRUMENTO_NO_ENCONTRADO_POR_ID("Instrumento no encontrado con ID: {}"),
    BUSCANDO_INSTRUMENTOS_POR_NOMBRE("Buscando instrumentos con nombre: {}"),
    INSTRUMENTO_ELIMINADO("Instrumento eliminado con ID: {}"),
    ELIMINANDO_INSTRUMENTO("Eliminando instrumento con ID: {}"),
    EDITANDO_INSTRUMENTO("Editando instrumento con ID: {}"),
    INSTRUMENTO_EDITADO("Instrumento editado: {}"),
    SUSCRIBIENDO_INVERSOR("Suscribiendo inversor {} al instrumento {}"),
    INVERSOR_YA_SUSCRITO("El inversor {} ya está suscrito al instrumento {}"),
    INVERSOR_SUSCRITO("Inversor {} suscrito al instrumento {}"),
    SOLICITUD_REGISTRAR_INSTRUMENTO("Solicitud para registrar instrumento recibida: {}"),
    SOLICITUD_BUSCAR_INSTRUMENTO_POR_ID("Solicitud para buscar instrumento por ID recibida: {}"),
    SOLICITUD_ELIMINAR_INSTRUMENTO("Solicitud para eliminar instrumento con ID recibida: {}"),
    SOLICITUD_EDITAR_INSTRUMENTO("Solicitud para editar instrumento con ID recibida: {}"),
    SOLICITUD_SUSCRIBIR_INVERSOR("Solicitud para suscribir inversor {} al instrumento {} recibida"),
    TODOS_LOS_INSTRUMENTOS_ENCONTRADOS("Todos los instrumentos han sido encontrados, cantidad: {}"),
    INSTRUMENTOS_ENCONTRADOS_POR_NOMBRE("Instrumentos encontrados con nombre {}: cantidad {}"),
    INSTRUMENTO_AGREGADO("Instrumento agregado correctamente: {}"),
    SOLICITUD_REGISTRAR_INVERSOR("Solicitud para agregar inversor: {}"),
    REGISTRANDO_INVERSOR("Registrando inversor: {}"),
    INVERSOR_DUPLICADO("Inversor duplicado con nombre: {}"),
    INVERSOR_REGISTRADO("Inversor registrado exitosamente: {}"),
    SOLICITUD_BUSCAR_INVERSOR_POR_ID("Solicitud para buscar inversor por ID: {}"),
    BUSCANDO_INVERSOR_POR_ID("Buscando inversor por ID: {}"),
    INVERSOR_NO_ENCONTRADO_POR_ID("Inversor no encontrado por ID: {}"),
    INVERSOR_ENCONTRADO("Inversor encontrado: {}"),
    SOLICITUD_FILTRAR_INVERSORES_POR_NOMBRE("Solicitud para filtrar inversores por nombre: {}"),
    INVERSORES_ENCONTRADOS_POR_NOMBRE("Inversores encontrados por nombre '{}': cantidad {}"),
    TODOS_LOS_INVERSORES_ENCONTRADOS("Listando todos los inversores. Total encontrados: {}"),
    SOLICITUD_ELIMINAR_INVERSOR("Solicitud para eliminar inversor con ID: {}"),
    ELIMINANDO_INVERSOR("Eliminando inversor con ID: {}"),
    INVERSOR_ELIMINADO("Inversor con ID {} eliminado"),
    INVERSOR_NO_ENCONTRADO_AL_ELIMINAR("Inversor no encontrado al intentar eliminar: {}"),
    SOLICITUD_EDITAR_INVERSOR("Solicitud para editar inversor con ID: {}"),
    EDITANDO_INVERSOR("Editando inversor con ID: {}"),
    INVERSOR_ACTUALIZADO("Inversor actualizado: {}"),
    INVERSOR_NO_ENCONTRADO_AL_EDITAR("Inversor no encontrado al intentar editar: {}"),
    INSTRUMENTO_NO_ENCONTRADO_URI("Instrumento no encontrado: {}, URI: {}"),
    INSTRUMENTO_DUPLICADO_URI("Instrumento duplicado: {}, URI: {}"),
    INVERSOR_NO_ENCONTRADO_URI("Inversor no encontrado: {}, URI: {}"),
    INVERSOR_DUPLICADO_URI("Inversor duplicado: {}, URI: {}"),
    ERROR_VALIDACION_URI("Error de validación: {}, URI: {}");
    private final String message;

    LogMessages(String message) {
        this.message = message;
    }
}
