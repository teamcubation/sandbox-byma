package  springbootApp.app.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class Accion extends InstrumentoFinanciero {

    public Accion(String nombre, double precio, Tipo tipo) {
        super(nombre, precio, tipo);
    }
}
