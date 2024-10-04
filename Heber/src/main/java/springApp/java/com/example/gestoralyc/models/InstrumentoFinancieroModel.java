package springApp.java.com.example.gestoralyc.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@Data
public abstract class InstrumentoFinancieroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_instrumento")
    private long id;

    private String nombre;
    private Double precio;
    private LocalDate fechaCreacion;

}


