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
@Setter
@Getter
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

//    public InstrumentoFinanciero(Long id, String nombre, double precio, String tipo, List<Inversor> inversoresSuscritosList) {
//        this.id = id;
//        this.nombre = nombre;
//        this.precio = precio;
//        this.tipo = tipo;
//        this.inversoresSuscritosList = inversoresSuscritosList;
//    }


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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Inversor> getInversoresSuscritosList() {
        return inversoresSuscritosList;
    }

    public void setInversoresSuscritosList(List<Inversor> inversoresSuscritosList) {
        this.inversoresSuscritosList = inversoresSuscritosList;
    }
}
