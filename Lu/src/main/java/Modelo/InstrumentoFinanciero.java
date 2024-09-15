package Modelo;

public abstract class InstrumentoFinanciero {
    private String nombre;
    private Double precio;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "\n" + this.getClass().getSimpleName() +
                "\n" + "Nombre: " + getNombre() +
                "\n" + "Precio: " + getPrecio() +
                "\n";
    }
}
