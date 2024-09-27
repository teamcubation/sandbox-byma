package springbootApp.java.models;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Accion extends InstrumentoFinanciero {
    public Accion(String nombre, double precio) {
        super(nombre, precio, Tipo.ACCION);
    }


}
