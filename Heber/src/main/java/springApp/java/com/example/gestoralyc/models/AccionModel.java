package springApp.java.com.example.gestoralyc.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity(name = "Accion")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Data
public class AccionModel extends InstrumentoFinancieroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accion_seq")
    @SequenceGenerator(name = "accion_seq", sequenceName = "accion_sequence", allocationSize = 1)
    private long id;

    private Double dividendo;
}
