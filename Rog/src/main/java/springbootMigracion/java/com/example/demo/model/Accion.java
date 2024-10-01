package springbootMigracion.java.com.example.demo.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Accion extends InstrumentoFinanciero {
    private static final String TIPO_ACCION = "Accion";
    public Accion(String nombre, double precio) {
        super(nombre, precio, TIPO_ACCION);
    }

    @Override
    public String toString() {
        return super.toString() + "} ";
    }
}
