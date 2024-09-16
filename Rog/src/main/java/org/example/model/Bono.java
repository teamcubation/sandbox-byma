package org.example.model;

public class Bono extends InstrumentoFinanciero{
    private double tasaDeInteres;

    public Bono(String nombre, Double precio, Double tasaDeInteres) {
        super(nombre, precio, "Bono");
        this.tasaDeInteres = tasaDeInteres;
    }

    public double getTasaDeInteres() {
        return tasaDeInteres;
    }

    public void setTasaDeInteres(double tasaDeInteres) {
        this.tasaDeInteres = tasaDeInteres;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", tasaDeInteres= '" + tasaDeInteres + '\'' +
                "} ";
    }

}
