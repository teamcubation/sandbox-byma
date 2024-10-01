package springApp.java.com.example.gestoralyc.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class FechaUtils {
    public static LocalDate calcularFinDelParking(LocalDate fechaInicial, int diasHabiles) {
        LocalDate finParking = fechaInicial;
        int diasAgregados = 0;

        while (diasAgregados < diasHabiles) {
            finParking = finParking.plusDays(1);
            if (finParking.getDayOfWeek() != DayOfWeek.SATURDAY && finParking.getDayOfWeek() != DayOfWeek.SUNDAY) {
                diasAgregados++;
            }
        }

        return finParking;
    }
}
