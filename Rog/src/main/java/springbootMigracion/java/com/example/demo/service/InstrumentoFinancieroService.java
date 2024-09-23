package springbootMigracion.java.com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootMigracion.java.com.example.demo.model.InstrumentoFinanciero;
import springbootMigracion.java.com.example.demo.repository.IInstrumentoFinancieroRepository;
//import springbootMigracion.java.com.example.demo.service.NotificadorService;

import java.util.List;
import java.util.Optional;

@Service
public class InstrumentoFinancieroService implements IInstrumentoFinancieroService {

    @Autowired
    private IInstrumentoFinancieroRepository instrumentoFinancieroRepository;

    @Override
    public void registrarInstrumento(InstrumentoFinanciero instrumento) {
        instrumentoFinancieroRepository.agregarInstrumento(instrumento);
    }

    @Override
    public void eliminarInstrumento(String nombre) {
        instrumentoFinancieroRepository.eliminarInstrumento(nombre);
    }

    @Override
    public List<InstrumentoFinanciero> listarTodosLosInstrumentos() {
        return instrumentoFinancieroRepository.listarTodosLosInstrumentos();
    }

    @Override
    public Optional<InstrumentoFinanciero> buscarInstrumentoPorNombre(String nombre) {
        return instrumentoFinancieroRepository.buscarInstrumentoPorNombre(nombre);
    }

    @Override
    public void editarInstrumento(String nombre, InstrumentoFinanciero nuevoInstrumento) {
        instrumentoFinancieroRepository.editarInstrumento(nombre, nuevoInstrumento);
//        notificadorService.notificar(nuevoInstrumento);
    }


}
