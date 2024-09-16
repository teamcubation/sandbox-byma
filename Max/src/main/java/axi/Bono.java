package axi;

public class Bono extends InstrumentoFinanciero {

    public Bono(String nombre, double precio) {
        super(nombre, precio, Tipo.BONO);
    }
}
