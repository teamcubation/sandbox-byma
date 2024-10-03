package com.example.project.model.instrumentoFinanciero;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.project.service.observer.Notificador;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "INSTRUMENTOFINANCIERO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
public abstract class InstrumentoFinanciero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    @Column(nullable = false)
    private double precio;

    @Column
    private LocalDate fechaDeEmision;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoInstrumentoFinanciero tipoInstrumentoFinanciero;

//    public String getNombre() {
//        return nombre;
//    }
//
//    public Double getPrecio() {
//        return precio;
//    }
//
//    // Aca imagino que se podría usar una expresion regular para validar el nombre del instrumento
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//        //Notificador.getInstance().notificarInteresados(this,"nombre");
//        // TODO: resolver notificador
//    }
//
//    public void setPrecio(Double precio) throws IllegalArgumentException{
//        if (precio < 0) {
//            throw new IllegalArgumentException("El precio no puede ser negativo");
//        }
//        this.precio = precio;
//        //Notificador.getInstance().notificarInteresados(this,"precio");
//    }
//
//    public LocalDate getFechaDeEmision() {
//        return fechaDeEmision;
//    }
//
//    public void setFechaDeEmision(LocalDate fechaDeEmision) {
//        this.fechaDeEmision = fechaDeEmision;
//    }
//
//    public TipoInstrumentoFinanciero getTipoInstrumentoFinanciero() {
//        return tipoInstrumentoFinanciero;
//    }
//
//    public void setTipoInstrumentoFinanciero(TipoInstrumentoFinanciero tipoInstrumentoFinanciero) {
//        this.tipoInstrumentoFinanciero = tipoInstrumentoFinanciero;
//    }

    @Override
    public String toString() {
        return "InstrumentoFinanciero{" + "nombre=" + this.getNombre() + ", precio=" + this.getPrecio() + ", fechaDeEmision=" + this.getFechaDeEmision().toString() + ", tipoInstrumentoFinanciero=" + this.getTipoInstrumentoFinanciero().toString() + '}';
    }
}
