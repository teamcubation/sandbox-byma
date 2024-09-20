package consoleversion.service.observer;


import consoleversion.exceptions.InversorNoEncontradoException;
import consoleversion.model.instrumentoFinanciero.InstrumentoFinanciero;
import consoleversion.repository.InversorRepository;

public class Notificador {

    private static Notificador instance;
    private InversorRepository inversorRepository;

    private Notificador() {
        this.inversorRepository = InversorRepository.getInstance();
    }

    public static Notificador getInstance() {
        if (instance == null) {
            instance = new Notificador();
        }
        return instance;
    }


    public void suscribirInversor(String nombre) throws InversorNoEncontradoException {
        this.inversorRepository.suscribirInversor(nombre);
    }

    public void desuscribirInversor(String nombre) throws InversorNoEncontradoException {
        this.inversorRepository.desuscribirInversor(nombre);
    }

    public void notificarInteresados(InstrumentoFinanciero instrumentoFinanciero, String atributo) {
       this.inversorRepository.obtenerInversoresSuscriptos().forEach(inversor -> inversor.update(instrumentoFinanciero, atributo));
    }
}
