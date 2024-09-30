package  springbootApp.app.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  springbootApp.app.exceptions.InstrumentoDuplicadoException;
import  springbootApp.app.exceptions.InstrumentoNoEncontradoException;
import  springbootApp.app.exceptions.InversorExistenteException;
import  springbootApp.app.exceptions.InversorNoEncontradoException;
import  springbootApp.app.models.InstrumentoFinanciero;
import  springbootApp.app.models.Inversor;
import  springbootApp.app.repositories.interfaces.IInstrumentoFinancieroRepository;
import  springbootApp.app.repositories.interfaces.IInversorRepository;

@Service
public class ObserverService {
    @Autowired
    private IInversorRepository inversorRepository;
    @Autowired
    private IInstrumentoFinancieroRepository instrumentosRepository;


    public void metodoParaSuscribirse(Long idInversor, Long idInstrumento) throws InversorNoEncontradoException, InversorExistenteException, InstrumentoDuplicadoException {
        InstrumentoFinanciero instrumento = instrumentosRepository.findById(idInstrumento).orElse(null);
        if (instrumento == null) {
            throw new InversorNoEncontradoException("Error. instrumento no existente");
        }
        Inversor inversor = inversorRepository.findById(idInversor).orElse(null);
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

    public void metodoParaDesuscribirse(Long id, Long idInstrumento) throws InversorNoEncontradoException, InstrumentoNoEncontradoException {
        InstrumentoFinanciero instrumento = instrumentosRepository.findById(idInstrumento).orElse(null);
        if (instrumento == null) {
            throw new InversorNoEncontradoException("Error. instrumento no existente");
        }
        Inversor inversor = inversorRepository.findById(id).orElse(null);
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
