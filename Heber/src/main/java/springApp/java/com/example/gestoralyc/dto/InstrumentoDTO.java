package springApp.java.com.example.gestoralyc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import springApp.java.com.example.gestoralyc.models.TipoInstrumento;
import java.time.DayOfWeek;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class InstrumentoDTO {

    private Long id;
    private TipoInstrumento tipo;
    private String nombre;
    private Double precio;
    private Double dividendo; // Solo para Accion
    private Double tasaInteres; // Solo para Bono
    private LocalDate fechaCreacion; // Fecha actual
    private LocalDate finDelParking; // Fecha de vencimiento del parking a 2 días hábiles
    // Getters y setters

    public TipoInstrumento getTipo() {
        return tipo;
    }

    public void setTipo(TipoInstrumento tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Double getDividendo() {
        return dividendo;
    }

    public void setDividendo(Double dividendo) {
        this.dividendo = dividendo;
    }

    public Double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(Double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public LocalDate getFinDelParking() {
        return finDelParking;
    }

    public void setFinDelParking(LocalDate finDelParking) {
        this.finDelParking = finDelParking;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



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

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }
}
