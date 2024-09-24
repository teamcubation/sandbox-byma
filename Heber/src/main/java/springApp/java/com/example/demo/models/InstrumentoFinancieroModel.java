package springApp.java.com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo",
        visible = true // Hacer visible el campo 'tipo' durante la deserialización
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AccionModel.class, name = "ACCION"),
        @JsonSubTypes.Type(value = BonoModel.class, name = "BONO")
})
public abstract class InstrumentoFinancieroModel {

    private Long id;
    private String nombre;
    private double precio;

    // Constructor vacío necesario para Jackson
    public InstrumentoFinancieroModel() {
        if (!(this instanceof AccionModel) && !(this instanceof BonoModel)) {
            throw new IllegalArgumentException("Tipo de instrumento no válido");
        }
    }

    // Constructor parametrizado
    public InstrumentoFinancieroModel(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "InstrumentoFinancieroModel{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }
}
