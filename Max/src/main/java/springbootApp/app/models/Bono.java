package  springbootApp.app.models;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class Bono extends InstrumentoFinanciero {
    public Bono(String nombre, double precio, Tipo tipo) {
        super(nombre, precio, tipo);
    }
}