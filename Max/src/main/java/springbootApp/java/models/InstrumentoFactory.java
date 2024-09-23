package springbootApp.java.models;

import org.springframework.stereotype.Component;

public class InstrumentoFactory {
    public static InstrumentoFinanciero nuevoInstrumento(String nombre, double precio, Tipo tipo) {
        InstrumentoFinanciero i;
        switch (tipo) {
            case Tipo.ACCION:
                i = new Accion(nombre, precio);
                break;
            case Tipo.BONO:
                i = new Bono(nombre, precio);
                break;
            default:
                throw new IllegalArgumentException("Error. Tipo invalido");
        }
        return i;
    }
}
