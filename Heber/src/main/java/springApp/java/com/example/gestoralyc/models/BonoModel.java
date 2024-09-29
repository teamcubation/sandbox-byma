package springApp.java.com.example.gestoralyc.models;


import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity(name="Bono")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Data
public class BonoModel extends InstrumentoFinancieroModel {
    private double tasaInteres;

}
