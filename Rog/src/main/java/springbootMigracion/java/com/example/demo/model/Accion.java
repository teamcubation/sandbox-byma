package springbootMigracion.java.com.example.demo.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Accion extends InstrumentoFinanciero {
    public Accion(String nombre, Double precio) {
        super(nombre, precio, "Acción");
    }

    @Override
    public String toString() {
        return super.toString() + "} ";
    }
}
