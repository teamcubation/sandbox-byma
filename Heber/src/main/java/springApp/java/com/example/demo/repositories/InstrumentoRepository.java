package springApp.java.com.example.demo.repositories;

import org.springframework.stereotype.Repository;
import consoleApp.models.InstrumentoFinanciero;
import springApp.java.com.example.demo.models.AccionModel;
import springApp.java.com.example.demo.models.BonoModel;
import springApp.java.com.example.demo.models.InstrumentoFinancieroModel;


import java.util.ArrayList;
import java.util.List;

@Repository
public class InstrumentoRepository {

    private List<InstrumentoFinancieroModel> instrumentosFinancieros = new ArrayList<>();
    private Long currentId = 1L; // Variable para gestionar el ID autoincremental

    public String helloRepository() {
        return "Hello from Repository";
    }

    public AccionModel agregarAccion(AccionModel accion) {
        accion.setId(currentId++); // Asignar el ID y luego incrementarlo
        instrumentosFinancieros.add(accion);
        return accion;
    }

    public BonoModel agregarBono(BonoModel bono) {
        bono.setId(currentId++); // Asignar el ID y luego incrementarlo
        instrumentosFinancieros.add(bono);
        return bono;
    }

    public List<InstrumentoFinancieroModel> obtenerInstrumentos() {
        return instrumentosFinancieros;
    }
}

