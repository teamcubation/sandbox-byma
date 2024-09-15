package Enums;

import Excepciones.OpcionInvalidaException;

public enum TiposInstrumentosFinancieros {
    BONO(1),
    ACCION(2);

    private final int opcion;

    TiposInstrumentosFinancieros(int opcion) {
        this.opcion = opcion;
    }

    public int getOpcion() {
        return opcion;
    }

    public static TiposInstrumentosFinancieros opcionSeleccionada(int opcionUsuario) {
        for (TiposInstrumentosFinancieros opcion : TiposInstrumentosFinancieros.values()) {
            if (opcion.getOpcion() == opcionUsuario) {
                return opcion;
            }
        }
        throw new OpcionInvalidaException("\n----------- El instrumento financiero a registrar no es valido. Por favor selecciona una opción valida entre las que figuran en pantalla. -----------\n");
    }
}