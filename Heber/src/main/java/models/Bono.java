package models;

public class Bono extends InstrumentoFinanciero {
    private static final String MSG_ERROR_TASA_INTERES = "La tasa de interes debe ser mayor o igual a cero";
    private double tasaInteres;

    public Bono(String nombre, double precio, double tasaInteres) {
        super(nombre, precio);
        setTasaInteres(tasaInteres);
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    private void setTasaInteres(double tasaInteres) {
        if (tasaInteres < 0) {
            throw new IllegalArgumentException(MSG_ERROR_TASA_INTERES);
        }
        this.tasaInteres = tasaInteres;
    }

    @Override
    public String toString() {
        return "Bono{" +
                " nombre= " + getNombre() +
                " precio= " + getPrecio() +
                " tasaInteres= " + tasaInteres +
                '}';
    }

    public void modificarTasaInteres(double nuevaTasaInteres) {
        setTasaInteres(nuevaTasaInteres);
    }

}
