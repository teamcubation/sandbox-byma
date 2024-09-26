package springbootApp.java.models;

import lombok.Data;

public class Accion extends InstrumentoFinanciero {
    public Accion(String nombre, double precio) {
        super(nombre, precio, Tipo.ACCION);
    }


}
