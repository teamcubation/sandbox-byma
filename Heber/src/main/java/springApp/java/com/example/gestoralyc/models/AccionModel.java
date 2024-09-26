package springApp.java.com.example.gestoralyc.models;

public class AccionModel extends InstrumentoFinancieroModel {
    private double dividendo;

    // Constructor sin el parámetro tipo, ya que se gestiona en la clase base
    public AccionModel(TipoInstrumento tipoInstrumento, String nombre, double precio, double dividendo) {
        super(tipoInstrumento, nombre, precio); // Llamamos al constructor de la clase base
        this.dividendo = dividendo;
    }

    public double getDividendo() {
        return dividendo;
    }

    public void setDividendo(double dividendo) {
        this.dividendo = dividendo;
    }

    @Override
    public String toString() {
        return "AccionModel{" +
                "nombre='" + getNombre() + '\'' +
                ", precio=" + getPrecio() +
                ", dividendo=" + getDividendo() +
                '}';
    }
}
