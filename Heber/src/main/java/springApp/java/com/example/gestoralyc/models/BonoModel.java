package springApp.java.com.example.gestoralyc.models;

public class BonoModel extends InstrumentoFinancieroModel {
    private double tasaInteres;

    public BonoModel(TipoInstrumento tipoInstrumento, String nombre, double precio, double tasaInteres) {
        super(tipoInstrumento, nombre, precio);
        this.tasaInteres = tasaInteres;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    @Override
    public String toString() {
        return "BonoModel{" +
                "nombre='" + getNombre() + '\'' +
                ", precio=" + getPrecio() +
                ", tasaInteres=" + tasaInteres +
                '}';
    }
}
