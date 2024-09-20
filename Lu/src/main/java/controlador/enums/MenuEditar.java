package controlador.enums;

import excepciones.OpcionInvalidaException;

public enum MenuEditar {
    EDITAR_NOMBRE(1),
    EDITAR_PRECIO(2);

    private final int opcion;

    MenuEditar(int opcion) {
        this.opcion = opcion;
    }

    public int getOpcion() {
        return opcion;
    }

    public static MenuEditar opcionSeleccionada(int opcionUsuario) {
        for (MenuEditar opcion : MenuEditar.values()) {
            if (opcion.getOpcion() == opcionUsuario) {
                return opcion;
            }
        }
        throw new OpcionInvalidaException("\n-------- Por favor selecciona una opción valida entre las que se muestran en el menú --------\n\n");
    }
}
