package springbootMigracion.java.com.example.demo.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Bono extends InstrumentoFinanciero {
    private static final String TIPO_BONO = "Bono";
    private double tasaDeInteres;

    public Bono(String nombre, Double precio, Double tasaDeInteres) {
        super(nombre, precio, TIPO_BONO);
        this.tasaDeInteres = tasaDeInteres;
    }
}
