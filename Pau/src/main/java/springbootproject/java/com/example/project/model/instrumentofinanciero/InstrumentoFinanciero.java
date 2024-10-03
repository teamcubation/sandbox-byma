package com.example.project.model.instrumentofinanciero;

import com.example.project.utils.Validador;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "INSTRUMENTOFINANCIERO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
public abstract class InstrumentoFinanciero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    protected String nombre;

    @Column(nullable = false)
    private double precio;

    @Column
    private LocalDate fechaDeEmision;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoInstrumentoFinanciero tipoInstrumentoFinanciero;

    public void setPrecio(double precio) {
        Validador.validarDoubleNoSeaNull(precio);
        Validador.validarDoublePositivo(precio);
        this.precio = precio;
    }

    public void setFechaDeEmision(LocalDate fechaDeEmision) {
        Validador.validarFechaNoSeaNull(fechaDeEmision);
        this.fechaDeEmision = fechaDeEmision;
    }
}
