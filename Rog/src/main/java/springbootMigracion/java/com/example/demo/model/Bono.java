package springbootMigracion.java.com.example.demo.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bono extends InstrumentoFinanciero {
    private double tasaDeInteres;

    public Bono(String nombre, Double precio, Double tasaDeInteres) {
        super(nombre, precio, "Bono");
        this.tasaDeInteres = tasaDeInteres;
    }


}
