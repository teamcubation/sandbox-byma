package com.example.project.service.observer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.project.exceptions.InversorNoEncontradoException;
import com.example.project.model.instrumentoFinanciero.InstrumentoFinanciero;
import com.example.project.repository.InversorRepository;

@Component
public class Notificador {
    private InversorRepository inversorRepository;

    @Autowired
    private Notificador(InversorRepository inversorRepository) {
        this.inversorRepository = inversorRepository;
    }

//    public static Notificador getInstance() {
//        if (instance == null) {
//            instance = new Notificador();
//        }
//        return instance;
//    }


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
