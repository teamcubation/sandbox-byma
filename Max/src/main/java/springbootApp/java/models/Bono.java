package springbootApp.java.models;


import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Bono extends InstrumentoFinanciero {
    public Bono(String nombre, double precio) {
        super(nombre, precio, Tipo.BONO);
    }

}
