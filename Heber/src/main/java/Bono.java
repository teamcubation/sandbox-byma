public class Bono extends InstrumentoFinanciero{
    private double tasaInteres;

    public Bono(String nombre, double precio, double tasaInteres) {
        super(nombre, precio);
        this.tasaInteres = tasaInteres;
    }
}
