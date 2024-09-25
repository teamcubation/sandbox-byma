package springbootApp.java.models;


public abstract class InstrumentoFinanciero {

    private String nombre;
    private double precio;
    private Tipo tipo;

    public InstrumentoFinanciero() {
    }

    public InstrumentoFinanciero(String nombre, double precio, Tipo tipo) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "InstrumentoFinanciero{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", tipo=" + tipo +
                '}';
    }

    public void setNombre(String nombre) {
        if (nombre == null) {
            throw new IllegalArgumentException("El nombre no puede ser nulo .");
        }
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser  vacío.");
        }
        this.nombre = nombre;
    }

    public void setTipo(Tipo tipo) {
        if (tipo == null) {
            throw new NullPointerException("El tipo no puede ser nulo .");
        }
        if (!tipo.equals(Tipo.ACCION) && !tipo.equals(Tipo.BONO)) {
            throw new IllegalArgumentException("Error. Tipo invalido");
        }
        this.tipo = tipo;
    }


    public void setPrecio(double precio) {
        try {
            if (precio > 0) {
                this.precio = precio;
            } else {
                throw new IllegalArgumentException("El precio no puede ser menor o igual a 0.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El precio debe ser un valor numérico.");
        }
    }

    public void actualizar(InstrumentoDTO instrumento) {
        this.setNombre(instrumento.getNombre());
        this.setTipo(instrumento.getTipo());
        this.setPrecio(instrumento.getPrecio());
    }
}
