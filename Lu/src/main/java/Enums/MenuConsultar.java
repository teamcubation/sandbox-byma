package Enums;

import Excepciones.OpcionInvalidaException;

public enum MenuConsultar {
    CONSULTAR_POR_NOMBRE(1),
    CONSULTAR_TODOS(2);

    private final int opcion;

    MenuConsultar(int opcion) {
        this.opcion = opcion;
    }

    public int getOpcion() {
        return opcion;
    }

    public static MenuConsultar opcionSeleccionada(int opcionUsuario) {
        for (MenuConsultar opcion : MenuConsultar.values()) {
            if (opcion.getOpcion() == opcionUsuario) {
                return opcion;
            }
        }
        throw new OpcionInvalidaException("\n-------- Por favor selecciona una opción valida entre las que se muestran en el menú --------\n\n");
    }
}
