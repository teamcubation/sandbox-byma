package springbootproject.java.com.example.project.model.instrumentoFinanciero;

import springbootproject.java.com.example.project.exceptions.NoExisteEseTipoDeInstrumentoException;

import java.time.LocalDate;

public class InstrumentoFinancieroDTO {
    private String nombre;
    private double precio;
    private LocalDate fechaDeEmision;
    private TipoInstrumentoFinanciero tipoInstrumentoFinanciero;

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

    public LocalDate getFechaDeEmision() {
        return fechaDeEmision;
    }

    public void setFechaDeEmision(LocalDate fechaDeEmision) {
        this.fechaDeEmision = fechaDeEmision;
    }

    public TipoInstrumentoFinanciero getTipoInstrumentoFinanciero() throws NoExisteEseTipoDeInstrumentoException {
        if (tipoInstrumentoFinanciero != TipoInstrumentoFinanciero.ACCION || tipoInstrumentoFinanciero != TipoInstrumentoFinanciero.BONO) {
            throw new NoExisteEseTipoDeInstrumentoException("El tipo ingresado no corresponde a un tipo de instrumento conocido.");
        } else {
            return tipoInstrumentoFinanciero;
        }
   }

    public void setTipoInstrumentoFinanciero(TipoInstrumentoFinanciero tipoInstrumentoFinanciero) {
        this.tipoInstrumentoFinanciero = tipoInstrumentoFinanciero;
    }
}
