package springbootMigracion.java.com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
//@Setter
//@Getter
//@ToString
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<InstrumentoFinanciero> getInstrumentosSuscritosList() {
        return instrumentosSuscritosList;
    }

    public void setInstrumentosSuscritosList(List<InstrumentoFinanciero> instrumentosSuscritosList) {
        this.instrumentosSuscritosList = instrumentosSuscritosList;
    }
}

