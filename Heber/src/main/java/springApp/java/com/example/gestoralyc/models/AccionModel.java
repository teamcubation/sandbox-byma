package springApp.java.com.example.gestoralyc.models;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity(name="Accion")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Data
public class AccionModel extends InstrumentoFinancieroModel {
    private double dividendo;

}
