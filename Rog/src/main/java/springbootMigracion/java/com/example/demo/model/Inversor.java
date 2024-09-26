package springbootMigracion.java.com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Inversor {
    private String nombre;
    private String email;
    private List<InstrumentoFinanciero> instrumentosSuscritos = new ArrayList<>();

    public Inversor(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<InstrumentoFinanciero> getInstrumentosSuscritos() {
        return instrumentosSuscritos;
    }

    public void setInstrumentosSuscritos(List<InstrumentoFinanciero> instrumentosSuscritos) {
        this.instrumentosSuscritos = instrumentosSuscritos;
    }

    @Override
    public String toString() {
        return "Inversor{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", instrumentosSuscritos=" + instrumentosSuscritos +
                '}';
    }
}

