package springApp.java.com.example.gestoralyc.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity(name = "instrumento_financiero")
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
@NoArgsConstructor
@Data

public abstract class InstrumentoFinancieroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_instrumento")
    private long id;
    private String nombre;
    private double precio;
    private LocalDate fechaCreacion;
    @Enumerated(EnumType.STRING)
    private TipoInstrumento tipo;

}


