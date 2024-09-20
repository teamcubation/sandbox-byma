package models;

public class Accion extends InstrumentoFinanciero {
    private static final String MSG_ERROR_DIVIDENDO = "El dividendo debe ser mayor o igual a cero";
    private double dividendo;    // Dividendos anuales (puede ser en pesos o porcentaje)

    public Accion(String nombre, double precio, double dividendo) {
        super(nombre, precio);
        setDividendo(dividendo);
    }

    public double getDividendo() {
        return dividendo;
    }

    private void setDividendo(double dividendo) {
        if (dividendo < 0) {
            throw new IllegalArgumentException(MSG_ERROR_DIVIDENDO);
        }
        this.dividendo = dividendo;
    }

    @Override
    public String toString() {
        return "Accion{" +
                " nombre= " + getNombre() +
                " precio= " + getPrecio() +
                " dividendo= " + dividendo +
                '}';
    }

    public void modificarDividendo(double nuevoDividendo) {
        setDividendo(nuevoDividendo);
    }

}
