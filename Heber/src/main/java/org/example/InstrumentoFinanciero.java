public abstract class InstrumentoFinanciero {
    private static final String MSG_ERROR_NULO = "%s nulo o vacío";
    private static final String MSG_ERROR_PRECIO = "Precio debe ser mayor o igual a cero";
    private String nombre;
    private double precio;

    public InstrumentoFinanciero(String nombre, double precio) throws  IllegalArgumentException {
        setNombre(nombre);
        setPrecio(precio);
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) throws  IllegalArgumentException{
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException(String.format(MSG_ERROR_NULO, "Nombre"));
        }
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    private void setPrecio(double precio) throws  IllegalArgumentException{
        if (precio < 0) {
            throw new IllegalArgumentException(MSG_ERROR_PRECIO);
        }
        this.precio = precio;
    }
}
