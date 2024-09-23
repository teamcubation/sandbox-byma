package springApp.java.com.example.demo.services;

import consoleApp.models.Accion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springApp.java.com.example.demo.models.AccionModel;
import springApp.java.com.example.demo.models.BonoModel;
import springApp.java.com.example.demo.models.InstrumentoFinancieroModel;
import springApp.java.com.example.demo.repositories.InstrumentoRepository;
import consoleApp.models.InstrumentoFinanciero;

import java.util.List;


@Service
public class InstrumentoService {

    @Autowired
    InstrumentoRepository instrumentoRepository;

    public String helloService() {
        return "Hello from Service";
    }

    public String helloRepository() {
        return instrumentoRepository.helloRepository();
    }

    ///////////METODOS CRUD//////////
    public AccionModel agregarAccion(AccionModel accion) {
        return instrumentoRepository.agregarAccion(accion);
    }

    public BonoModel agregarBono(BonoModel bono) {
        return instrumentoRepository.agregarBono(bono);
    }

    public List<InstrumentoFinancieroModel> obtenerInstrumentos() {
        return instrumentoRepository.obtenerInstrumentos();
    }


}
