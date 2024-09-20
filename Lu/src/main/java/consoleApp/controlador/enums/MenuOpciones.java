package consoleApp.controlador.enums;

import consoleApp.excepciones.OpcionInvalidaException;

public enum MenuOpciones {
    REGISTRAR(1),
    CONSULTAR(2),
    EDITAR(3),
    ELIMINAR(4),
    SALIR(5);

    private final int opcion;

    MenuOpciones(int opcion) {
        this.opcion = opcion;
    }

    public int getOpcion() {
        return opcion;
    }

    public static MenuOpciones opcionSeleccionada(int opcionUsuario) {
        for (MenuOpciones opcion : MenuOpciones.values()) {
            if (opcion.getOpcion() == opcionUsuario) {
                return opcion;
            }
        }
        throw new OpcionInvalidaException("\n-------- Por favor selecciona una opción valida entre las que se muestran en el menú --------\n\n");
    }
}
