package springApp.java.com.example.gestoralyc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Data
public class BonoDTO {
    private Long id;
    private String nombre;
    private Double precio;
    private Double tasaInteres;
    @JsonIgnore
    private LocalDate fechaCreacion;
    private LocalDate finDelParking; // Fecha de vencimiento del parking a 2 días hábiles

    public void calcularFinDelParking() {
        LocalDate now = LocalDate.now();
        LocalDate finParking = now;
        //System.out.println("Fecha actual: " + now);
        int diasAgregados = 0;

        while (diasAgregados < 2) {
            finParking = finParking.plusDays(1);
            //System.out.println("Fecha agregada: " + finParking);
            //System.out.println("Día de la semana: " + finParking.getDayOfWeek());
            // Contar solo si es un día hábil
            if (finParking.getDayOfWeek() != DayOfWeek.SATURDAY && finParking.getDayOfWeek() != DayOfWeek.SUNDAY) {
                diasAgregados++;
            }
        }

        this.finDelParking = finParking;
    }

    public Double getTasaInteres() {
        return tasaInteres;
    }

}
