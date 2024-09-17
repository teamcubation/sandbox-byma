public class Accion extends InstrumentoFinanciero{
    private double dividendo;    // Dividendos anuales (puede ser en pesos o porcentaje)

    public Accion(String nombre, double precio, double dividendo) {
        super(nombre, precio);
        this.dividendo = dividendo;
    }
}
