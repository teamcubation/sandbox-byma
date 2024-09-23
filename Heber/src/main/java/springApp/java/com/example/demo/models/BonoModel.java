package springApp.java.com.example.demo.models;

public class BonoModel extends InstrumentoFinancieroModel {
    private double tasaInteres;

    public BonoModel(String nombre, double precio, double tasaInteres) {
        super(nombre, precio);
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
