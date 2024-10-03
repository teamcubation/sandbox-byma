package com.example.teamcubation.service.impl;

import com.example.teamcubation.exceptions.InstrumentoDuplicadoException;
import com.example.teamcubation.exceptions.InstrumentoNoEncontradoException;
import com.example.teamcubation.model.Bono;
import com.example.teamcubation.repository.interfaces.BonoRepository;
import com.example.teamcubation.service.BonoService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BonoServiceImpl implements BonoService {


    private final BonoRepository bonoRepository;


    private final AccionServiceImpl accionService;


    public BonoServiceImpl(BonoRepository bonoRepository, @Lazy AccionServiceImpl accionService) {
        this.bonoRepository = bonoRepository;
        this.accionService = accionService;
    }


    /**
     * Crea un nuevo instrumento bono y lo agrega a la lista de instrumentos financieros.
     * <p>
     * El instrumento bono se busca por su nombre, si ya existe uno con el mismo nombre
     * se lanza una excepcion {@link InstrumentoDuplicadoException}.
     * </p>
     * <p>
     * Si el instrumento bono se crea correctamente, se devuelve un objeto
     * {@link Bono} con los nuevos valores.
     * </p>
     *
     * @param nuevoBono El instrumento bono a crear
     * @return El instrumento bono creado
     * @throws InstrumentoDuplicadoException Si el instrumento ya existe
     */
    @Override
    public Bono createBono(Bono nuevoBono) throws InstrumentoDuplicadoException {

        if (this.instrumentoEsDuplicado(nuevoBono.getNombre())) {
            throw new InstrumentoDuplicadoException("Error: Ya existe un bono con ese nombre!!!");
        }
        return this.bonoRepository.save(nuevoBono);
    }

    @Override
    public List<Bono> getAllBonos() {
        return this.bonoRepository.findAll();
    }

    /**
     * Actualiza un instrumento bono existente.
     * <p>
     * El instrumento bono se busca por su id, si no se encuentra se lanza una excepcion
     * {@link InstrumentoNoEncontradoException}.
     * </p>
     * <p>
     * Si el instrumento bono se actualiza correctamente, se devuelve un objeto
     * {@link Bono} con los nuevos valores.
     * </p>
     * <p>
     * Si el nombre del instrumento bono ya existe en la base de datos, se lanza una excepcion
     * {@link InstrumentoDuplicadoException}.
     * </p>
     *
     * @param bonoActualizado El instrumento bono a actualizar
     * @return El instrumento bono actualizado
     * @throws InstrumentoNoEncontradoException Si no se encuentra el instrumento bono
     * @throws InstrumentoDuplicadoException    Si el nombre del instrumento bono ya existe
     */
    @Override
    public Bono updateBono(Bono bonoActualizado) throws InstrumentoNoEncontradoException, InstrumentoDuplicadoException {

        if (this.bonoEsInexistente(bonoActualizado.getId())) {
            throw new InstrumentoNoEncontradoException("Error: El bono no existe");
        }

        if (this.instrumentoEsDuplicado(bonoActualizado.getNombre())) {
            throw new InstrumentoDuplicadoException("Error: Ya existe un bono con ese nombre");
        }

        return this.bonoRepository.save(bonoActualizado);
    }

    @Override
    public void deleteBono(long id) throws InstrumentoNoEncontradoException {

        if (bonoEsInexistente(id)) {
            throw new InstrumentoNoEncontradoException("Error: El bono con el id: " + id + " no existe");
        }

        this.bonoRepository.deleteById(id);
    }

    //Validaciones
    private boolean bonoEsInexistente(long id) {

        return !this.bonoRepository
                .existsById(id);
    }

    private boolean instrumentoEsDuplicado(String nombre) {
        return bonoEsDuplicado(nombre) || accionEsDuplicado(nombre);
    }

    public boolean bonoEsDuplicado(String nombre) {
        return this.bonoRepository
                .findAll()
                .stream()
                .anyMatch(bono -> bono.getNombre().equalsIgnoreCase(nombre)) || this.accionService.accionEsDuplicado(nombre);
    }

    private boolean accionEsDuplicado(String nombre) {
        return accionService.getAllAcciones()
                .stream()
                .anyMatch(accion -> accion.getNombre().equalsIgnoreCase(nombre));
    }
}
