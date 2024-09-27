package springbootApp.java.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootApp.java.exceptions.InstrumentoDuplicadoException;
import springbootApp.java.exceptions.InstrumentoNoEncontradoException;
import springbootApp.java.exceptions.InversorExistenteException;
import springbootApp.java.exceptions.InversorNoEncontradoException;
import springbootApp.java.models.InstrumentoFinanciero;
import springbootApp.java.models.Inversor;
import springbootApp.java.repositories.interfaces.IInstrumentoFinancieroRepository;
import springbootApp.java.repositories.interfaces.IInversorRepository;

@Service
public class ObserverService {
    @Autowired
    private IInversorRepository inversorRepository;
    @Autowired
    private IInstrumentoFinancieroRepository instrumentosRepository;


    public void metodoParaSuscribirse(String dni, String nombreInstrumento) throws InversorNoEncontradoException, InversorExistenteException, InstrumentoDuplicadoException {
        InstrumentoFinanciero instrumento = instrumentosRepository.findByNombre(nombreInstrumento);
        if (instrumento == null) {
            throw new InversorNoEncontradoException("Error. instrumento no existente");
        }
        Inversor inversor = inversorRepository.findByDni(dni);
        if (inversor == null) {
            throw new InversorNoEncontradoException("Error. inversor no existente");
        }
        if (this.inversorTieneInstrumento(inversor, instrumento)) {
            throw new InstrumentoDuplicadoException("Error. ya estas suscripto a este instrumento");
        }
        inversor.getInstrumentosDelInversor().add(instrumento);
        instrumento.getInversoresList().add(inversor);
        inversorRepository.save(inversor);
        instrumentosRepository.save(instrumento);
    }

    public void metodoParaDesuscribirse(String dni, String nombreInstrumento) throws InversorNoEncontradoException, InstrumentoNoEncontradoException {
        InstrumentoFinanciero instrumento = instrumentosRepository.findByNombre(nombreInstrumento);
        if (instrumento == null) {
            throw new InversorNoEncontradoException("Error. instrumento no existente");
        }
        Inversor inversor = inversorRepository.findByDni(dni);
        if (inversor == null) {
            throw new InversorNoEncontradoException("Error. inversor no existente");
        }
        if (!this.inversorTieneInstrumento(inversor, instrumento)) {
            throw new InstrumentoNoEncontradoException("Error. no estas suscripto a este instrumento");
        }
        inversor.getInstrumentosDelInversor().remove(instrumento);
        instrumento.getInversoresList().remove(inversor);
        inversorRepository.save(inversor);
        instrumentosRepository.save(instrumento);
    }
    private boolean inversorTieneInstrumento(Inversor inversor, InstrumentoFinanciero instrumento) {
        return inversor.getInstrumentosDelInversor().stream().anyMatch(i -> i.equals(instrumento));
    }

    public static void notificarCambioDePrecio(InstrumentoFinanciero instrumento) {
        for (Inversor inversor : instrumento.getInversoresList()) {
            System.out.println(inversor.getNombre() + ": el precio del instrumento " + instrumento.getNombre() + " cambio a "
                    + instrumento.getPrecio());
        }
    }
}
