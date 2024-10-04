package springApp.java.com.example.gestoralyc.models;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity(name = "Bono")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Data
public class BonoModel extends InstrumentoFinancieroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bono_seq")
    @SequenceGenerator(name = "bono_seq", sequenceName = "bono_sequence", allocationSize = 1)
    private long id;

    private double tasaInteres;
}
