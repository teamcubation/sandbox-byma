package springbootMigracion.java.com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootMigracion.java.com.example.demo.dto.InversorDTO;
import springbootMigracion.java.com.example.demo.exception.InversorDuplicadoException;
import springbootMigracion.java.com.example.demo.exception.InversorNoEncontradoException;
import springbootMigracion.java.com.example.demo.model.Inversor;
import springbootMigracion.java.com.example.demo.repository.IInversorRepository;
import springbootMigracion.java.com.example.demo.utils.validations.ErrorMessages;
import springbootMigracion.java.com.example.demo.utils.validations.ValidatorInversor;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class InversorService implements IInversorService {

    @Autowired
    private IInversorRepository inversorRepository;

    @Override
    public Inversor registrarInversor(InversorDTO inversorDTO) throws Exception {
        log.info("Registrando inversor: {}", inversorDTO);
        ValidatorInversor.validarInversor(inversorDTO);
        Inversor inversor = Inversor.builder()
                .nombre(inversorDTO.getNombre())
                .email(inversorDTO.getEmail())
                .build();
        if (inversorRepository.existsByNombreIgnoreCase(inversor.getNombre())){
            log.warn("Inversor duplicado: {}", inversor.getNombre());
            throw new InversorDuplicadoException(ErrorMessages.ERROR_INVERSOR_DUPLICADO);
        }
        Inversor inversorGuardado = inversorRepository.save(inversor);
        log.info("Inversor registrado exitosamente: {}", inversorGuardado);
        return inversorGuardado;
    }

    @Override
    public Inversor buscarInversorPorId(Long id) throws Exception {
        log.info("Buscando inversor por ID: {}", id);
        Optional<Inversor> optionalInversor = inversorRepository.findById(id);
        if (optionalInversor.isPresent()){
            log.info("Inversor encontrado: {}", optionalInversor.get());
            return optionalInversor.get();
        } else {
            log.warn("Inversor no encontrado por ID: {}", id);
            throw new InversorNoEncontradoException(ErrorMessages.ERROR_INVERSOR_NO_ENCONTRADO);
        }
    }

    @Override
    public void eliminarInversor(Long id) throws Exception {
        log.info("Eliminando inversor con ID: {}", id);
        Inversor inversor = inversorRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Inversor no encontrado al intentar eliminar: {}", id);
                    return new InversorNoEncontradoException(ErrorMessages.ERROR_INVERSOR_NO_ENCONTRADO);
                });
        inversorRepository.delete(inversor);
        log.info("Inversor con ID {} eliminado", id);
    }

    @Override
    public List<Inversor> listarTodosLosInversores() {
        return inversorRepository.findAll();
    }

    @Override
    public List<Inversor> listarInversoresPorNombre(String nombre) {
        log.info("Buscando inversores por nombre: {}", nombre);
        List<Inversor> inversores = inversorRepository.findAllByNombreContainingIgnoreCase(nombre);
        log.info("Inversores encontrados: {}", inversores.size());
        return inversores;
    }

    @Override
    public Inversor editarInversor(Long id, InversorDTO nuevoInversorDTO) throws Exception {
        log.info("Editando inversor con ID: {}", id);
        ValidatorInversor.validarInversor(nuevoInversorDTO);
        Inversor inversor = inversorRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Inversor no encontrado al intentar editar: {}", id);
                    return new InversorNoEncontradoException(ErrorMessages.ERROR_INVERSOR_NO_ENCONTRADO);
                });
        inversor.setNombre(nuevoInversorDTO.getNombre());
        inversor.setEmail(nuevoInversorDTO.getEmail());
        log.info("Inversor actualizado: {}", inversor);
        return inversorRepository.save(inversor);
    }

}
