package springBootProject.java.com.example.proyectoSpringBoot.service.factory.enums;


import springBootProject.java.com.example.proyectoSpringBoot.excepciones.OpcionInvalidaException;

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

    public static TiposInstrumentosFinancieros opcionSeleccionada(Integer opcionUsuario) throws OpcionInvalidaException {
        for (TiposInstrumentosFinancieros opcion : TiposInstrumentosFinancieros.values()) {
            if (opcion.getOpcion() == opcionUsuario) {
                return opcion;
            }
        }
        throw new OpcionInvalidaException("Por favor ingrese un instrumento financieron valido.");
    }
}