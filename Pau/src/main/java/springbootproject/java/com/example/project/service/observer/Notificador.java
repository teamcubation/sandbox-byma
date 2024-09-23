package springbootproject.java.com.example.project.service.observer;


import org.springframework.stereotype.Component;
import springbootproject.java.com.example.project.exceptions.InversorNoEncontradoException;
import springbootproject.java.com.example.project.model.instrumentoFinanciero.InstrumentoFinanciero;
import springbootproject.java.com.example.project.repository.InversorRepository;

@Component
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
