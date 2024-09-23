package springbootproject.java.com.example.project.model.instrumentoFinanciero;

import springbootproject.java.com.example.project.service.observer.Notificador;

import java.time.LocalDate;

public abstract class InstrumentoFinanciero {
    private String nombre;
    private double precio;
    private LocalDate fechaDeEmision;
    private TipoInstrumentoFinanciero tipoInstrumentoFinanciero;

    public InstrumentoFinanciero() {
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    // Aca imagino que se podría usar una expresion regular para validar el nombre del instrumento
    public void setNombre(String nombre) {
        this.nombre = nombre;
        Notificador.getInstance().notificarInteresados(this,"nombre");
    }

    public void setPrecio(Double precio) throws IllegalArgumentException{
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precio = precio;
        Notificador.getInstance().notificarInteresados(this,"precio");
    }

    public LocalDate getFechaDeEmision() {
        return fechaDeEmision;
    }

    public void setFechaDeEmision(LocalDate fechaDeEmision) {
        this.fechaDeEmision = fechaDeEmision;
    }

    public TipoInstrumentoFinanciero getTipoInstrumentoFinanciero() {
        return tipoInstrumentoFinanciero;
    }

    public void setTipoInstrumentoFinanciero(TipoInstrumentoFinanciero tipoInstrumentoFinanciero) {
        this.tipoInstrumentoFinanciero = tipoInstrumentoFinanciero;
    }

    @Override
    public String toString() {
        return "InstrumentoFinanciero{" + "nombre=" + this.getNombre() + ", precio=" + this.getPrecio() + ", fechaDeEmision=" + this.getFechaDeEmision().toString() + ", tipoInstrumentoFinanciero=" + this.getTipoInstrumentoFinanciero().toString() + '}';
    }
}
