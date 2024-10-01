package springbootMigracion.java.com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
//@Setter
//@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class InstrumentoFinanciero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private double precio;
    private String tipo;
    @ManyToMany(mappedBy = "instrumentosSuscritosList", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
//    @JsonBackReference
    private List<Inversor> inversoresSuscritosList = new ArrayList<>();;

    public InstrumentoFinanciero(String nombre, double precio, String tipo) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
    }
}
