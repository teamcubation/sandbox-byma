package springbootApp.java.models;


public class Accion extends InstrumentoFinanciero {
    public Accion(String nombre, double precio) {
        super(nombre, precio, Tipo.ACCION);
    }


}
