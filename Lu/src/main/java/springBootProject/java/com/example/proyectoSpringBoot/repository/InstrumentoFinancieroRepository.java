package springBootProject.java.com.example.proyectoSpringBoot.repository;

import org.springframework.stereotype.Repository;
import springBootProject.java.com.example.proyectoSpringBoot.model.Accion;
import springBootProject.java.com.example.proyectoSpringBoot.model.Bono;
import springBootProject.java.com.example.proyectoSpringBoot.model.InstrumentoFinanciero;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InstrumentoFinancieroRepository {
    private List<InstrumentoFinanciero> instrumentosFinancieros = new ArrayList<>();

    public InstrumentoFinancieroRepository() {
        InstrumentoFinanciero bono = new Bono();
        InstrumentoFinanciero accion = new Accion();

        bono.setNombre("Bono prueba");
        bono.setPrecio(888);

        accion.setNombre("Accion prueba");
        accion.setPrecio(444);

        instrumentosFinancieros.add(bono);
        instrumentosFinancieros.add(accion);
    }

    public Optional<InstrumentoFinanciero> buscarInstrumentoFinanciero(String nombre) {
        return  this.instrumentosFinancieros.stream()
                .filter(instrumento -> instrumento.getNombre().equals(nombre))
                .findFirst();
    }

    public List<InstrumentoFinanciero> consultarTodos() {
        return this.instrumentosFinancieros;
    }

    public Optional<InstrumentoFinanciero> consultar(String nombre) {
        return buscarInstrumentoFinanciero(nombre);
    }

    public void registrar(InstrumentoFinanciero instrumentoFinanciero) {
        this.instrumentosFinancieros.add(instrumentoFinanciero);
    }

    public void eliminar(InstrumentoFinanciero instrumentoFinanciero) {
        this.instrumentosFinancieros.remove(instrumentoFinanciero);
    }
}
