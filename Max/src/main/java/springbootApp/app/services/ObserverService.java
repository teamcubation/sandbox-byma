package  springbootApp.app.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  springbootApp.app.exceptions.InstrumentoDuplicadoException;
import  springbootApp.app.exceptions.InstrumentoNoEncontradoException;
import  springbootApp.app.exceptions.InversorExistenteException;
import  springbootApp.app.exceptions.InversorNoEncontradoException;
import  springbootApp.app.models.InstrumentoFinanciero;
import  springbootApp.app.models.Inversor;
import springbootApp.app.services.interfaces.IInstrumentoFinancieroService;
import springbootApp.app.services.interfaces.IInversorService;
import springbootApp.app.services.interfaces.IObserverService;

@Service
public class ObserverService implements IObserverService {
    @Autowired
    private IInstrumentoFinancieroService instrumentoFinancieroService;
    @Autowired
    private IInversorService inversorService;


    public void metodoParaSuscribirse(Long idInversor, Long idInstrumento) throws InversorNoEncontradoException, InversorExistenteException, InstrumentoDuplicadoException {
        InstrumentoFinanciero instrumento = instrumentoFinancieroService.buscarInstrumentoPorID(idInstrumento);
        Inversor inversor = inversorService.consultarInversor(idInversor);
        if (this.inversorTieneInstrumento(inversor, instrumento)) {
            throw new InstrumentoDuplicadoException("Error. ya estas suscripto a este instrumento");
        }
        inversor.getInstrumentosDelInversor().add(instrumento);
        instrumento.getInversoresList().add(inversor);
        inversorService.guardarInversor(inversor);
        instrumentoFinancieroService.guardarInstrumento(instrumento);
    }

    public void metodoParaDesuscribirse(Long idInversor, Long idInstrumento) throws InversorNoEncontradoException, InstrumentoNoEncontradoException {
        InstrumentoFinanciero instrumento = instrumentoFinancieroService.buscarInstrumentoPorID(idInstrumento);
        Inversor inversor = inversorService.consultarInversor(idInversor);

        if (!this.inversorTieneInstrumento(inversor, instrumento)) {
            throw new InstrumentoNoEncontradoException("Error. no estas suscripto a este instrumento");
        }
        inversor.getInstrumentosDelInversor().remove(instrumento);
        instrumento.getInversoresList().remove(inversor);
        inversorService.guardarInversor(inversor);
        instrumentoFinancieroService.guardarInstrumento(instrumento);
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