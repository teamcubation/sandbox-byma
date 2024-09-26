package springApp.java.com.example.gestoralyc.models;

import java.time.LocalDate;

public abstract class InstrumentoFinancieroModel {

    private Long id;
    private TipoInstrumento tipo;
    private String nombre;
    private double precio;
    private LocalDate fechaCreacion;

    // Constructor vacío
    public InstrumentoFinancieroModel() {}

    // Constructor parametrizado
    public InstrumentoFinancieroModel(TipoInstrumento tipoInstrumento, String nombre, double precio) {
        this.tipo = tipoInstrumento;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaCreacion = LocalDate.now(); // Fecha actual
    }

    // Getters y setters
    public TipoInstrumento getTipo() {
        return tipo;
    }

    public void setTipo(TipoInstrumento tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "InstrumentoFinancieroModel{" + "id=" + id + ", nombre='" + nombre + '\'' + ", precio=" + precio + '}';
    }
}
