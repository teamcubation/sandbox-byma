package springbootMigracion.java.com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inversor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "inversor_instrumento",
            joinColumns = @JoinColumn(name = "inversor_id"),
            inverseJoinColumns = @JoinColumn(name = "instrumento_id")
    )
//    @JsonManagedReference
    private List<InstrumentoFinanciero> instrumentosSuscritosList = new ArrayList<>();

    public Inversor(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

}

