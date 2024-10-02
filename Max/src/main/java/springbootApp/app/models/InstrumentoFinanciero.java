package  springbootApp.app.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class InstrumentoFinanciero {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Double precio;
    private Tipo tipo;
    @ManyToMany(mappedBy = "instrumentosDelInversor", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private List <Inversor> inversoresList;

    public InstrumentoFinanciero(String nombre, Double precio, Tipo tipo) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.inversoresList = new ArrayList<>();
    }
}