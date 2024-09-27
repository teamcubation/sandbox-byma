package springbootApp.java.models;

import jakarta.persistence.*;
import lombok.*;
import springbootApp.java.exceptions.InstrumentoDuplicadoException;
import springbootApp.java.exceptions.InstrumentoNoEncontradoException;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Inversor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String dni;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "inversor_instrumento", joinColumns = @JoinColumn(name = "inversor_id"),
            inverseJoinColumns = @JoinColumn(name = "instrumento_id"))
    private List<InstrumentoFinanciero> instrumentosDelInversor;

    public Inversor(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
        instrumentosDelInversor = new ArrayList<>();
    }
}