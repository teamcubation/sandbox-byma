package springBootProject.java.com.example.proyectoSpringBoot.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springBootProject.java.com.example.proyectoSpringBoot.dto.InstrumentoFinancieroDTO;
import springBootProject.java.com.example.proyectoSpringBoot.excepciones.customExcepcions.InstrumentoDuplicadoException;
import springBootProject.java.com.example.proyectoSpringBoot.excepciones.customExcepcions.InstrumentoNoEncontradoException;
import springBootProject.java.com.example.proyectoSpringBoot.excepciones.customExcepcions.OpcionInvalidaException;
import springBootProject.java.com.example.proyectoSpringBoot.model.InstrumentoFinanciero;
import springBootProject.java.com.example.proyectoSpringBoot.repository.InstrumentoFinancieroRepository;
import springBootProject.java.com.example.proyectoSpringBoot.service.InstrumentoFinancieroService;
import springBootProject.java.com.example.proyectoSpringBoot.service.factory.InstrumentoFinancieroFactory;
import springBootProject.java.com.example.proyectoSpringBoot.utils.Validaciones;

import java.util.List;
import java.util.Optional;


@Service
public class InstrumentoFinancieroServiceImpl implements InstrumentoFinancieroService {

    @Autowired
    InstrumentoFinancieroRepository instrumentoFinancieroRepository;

    public List<InstrumentoFinanciero> consultarTodos() {
        return this.instrumentoFinancieroRepository.consultarTodos();
    }

    public InstrumentoFinanciero consultar(String nombre) throws InstrumentoNoEncontradoException {
        Optional<InstrumentoFinanciero> instrumentoFinanciero = this.instrumentoFinancieroRepository.consultar(nombre);

        if (instrumentoFinanciero.isEmpty()) {
            throw new InstrumentoNoEncontradoException("El instrumento financiero con ese nombre no existe.");
        }

        return instrumentoFinanciero.get();
    }

    public InstrumentoFinanciero registrar(InstrumentoFinancieroDTO instrumentoFinancieroDTO) throws InstrumentoDuplicadoException, OpcionInvalidaException {
        validarDatos(instrumentoFinancieroDTO);

        Optional<InstrumentoFinanciero> instrumentoFinanciero = this.instrumentoFinancieroRepository.buscar(instrumentoFinancieroDTO.getNombre());

        if (instrumentoFinanciero.isPresent()) {
            throw new InstrumentoDuplicadoException("El instrumento financiero a registrar ya existe, no se permiten instrumentos duplicados.");
        }

        InstrumentoFinanciero instrumentoFinancieroNuevo = InstrumentoFinancieroFactory.crearInstrumentoFinanciero(instrumentoFinancieroDTO.getTipo());
        instrumentoFinancieroNuevo.setPrecio(instrumentoFinancieroDTO.getPrecio());
        instrumentoFinancieroNuevo.setNombre(instrumentoFinancieroDTO.getNombre());
        instrumentoFinancieroNuevo.setTipo(instrumentoFinancieroDTO.getTipo());

        return this.instrumentoFinancieroRepository.registrar(instrumentoFinancieroNuevo);
    }

    public void eliminar(String nombre) throws InstrumentoNoEncontradoException {
        Optional<InstrumentoFinanciero> instrumentoFinanciero = this.instrumentoFinancieroRepository.buscar(nombre);

        if (instrumentoFinanciero.isEmpty()) {
            throw new InstrumentoNoEncontradoException("El instrumento financiero a eliminar no existe.");
        }

        this.instrumentoFinancieroRepository.eliminar(instrumentoFinanciero.get());
    }

    public InstrumentoFinanciero editar(String instrumentoAEditar, InstrumentoFinancieroDTO instrumentoFinancieroDTO) throws InstrumentoNoEncontradoException, OpcionInvalidaException {
        validarNombre(instrumentoAEditar);
        validarDatos(instrumentoFinancieroDTO);

        Optional<InstrumentoFinanciero> instrumentoFinanciero = this.instrumentoFinancieroRepository.buscar(instrumentoAEditar);

        if (instrumentoFinanciero.isEmpty()) {
            throw new InstrumentoNoEncontradoException("El instrumento financiero a editar no existe.");
        }

        InstrumentoFinanciero instrumentoFinancieroAEditar = instrumentoFinanciero.get();
        instrumentoFinancieroAEditar.setNombre(instrumentoFinancieroDTO.getNombre());
        instrumentoFinancieroAEditar.setPrecio(instrumentoFinancieroDTO.getPrecio());

        return instrumentoFinancieroAEditar;
    }

    private void validarNombre(String nombreAValidar) throws OpcionInvalidaException {
        if (!Validaciones.esNombreValido(nombreAValidar)) {
            throw new OpcionInvalidaException("Por favor ingrese un nombre valido");
        }
    }

    private void validarPrecio(Double precioAValidar) throws OpcionInvalidaException {
        if (!Validaciones.esPrecioValido(precioAValidar)) {
            throw new OpcionInvalidaException("Por favor ingrese un precio valido.");
        }
    }

    private void validarTipo(Integer tipoAValidar) throws OpcionInvalidaException {
        if (!Validaciones.esTipoValido(tipoAValidar)) {
            throw new OpcionInvalidaException("Por favor ingrese un instrumento financieron valido.");
        }
    }

    private void validarDatos(InstrumentoFinancieroDTO instrumentoFinancieroDTO) throws OpcionInvalidaException {
        validarNombre(instrumentoFinancieroDTO.getNombre());
        validarPrecio(instrumentoFinancieroDTO.getPrecio());
        validarTipo(instrumentoFinancieroDTO.getTipo());
    }
}
