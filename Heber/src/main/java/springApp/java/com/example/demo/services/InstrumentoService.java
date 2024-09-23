package springApp.java.com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springApp.java.com.example.demo.repositories.InstrumentoRepository;

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
}
