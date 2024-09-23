package springbootApp.java.models;

import org.springframework.stereotype.Component;

public class Bono extends InstrumentoFinanciero {
    public Bono(String nombre, double precio) {
        super(nombre, precio, Tipo.BONO);
    }

}
