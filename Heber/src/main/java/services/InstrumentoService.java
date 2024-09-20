package services;

import contenedorBoot.ContenedorBoot;
import controllers.InstrumentoController;
import exceptions.InstrumentoDuplicadoException;
import exceptions.InstrumentoNoEncontradoException;
import models.*;
import repositories.InstrumentoRepository;

import java.util.Scanner;

public class InstrumentoService {

    private static InstrumentoService instrumentoService;
    private Scanner scanner = new Scanner(System.in);
    private InstrumentoRepository instrumentoRepository = InstrumentoRepository.getInstance();

    private InstrumentoService() {
        this.scanner = new Scanner(System.in);
    }

    // Método para obtener la única instancia
    public static InstrumentoService getInstance() {
        if (instrumentoService == null) {
            instrumentoService = new InstrumentoService();
        }
        return instrumentoService;
    }

    public InstrumentoFinanciero buscarInstrumentoPorNombre(String nombreInstrumento) throws InstrumentoNoEncontradoException {
        InstrumentoFinanciero instrumento = instrumentoRepository.buscarInstrumentoPorNombre(nombreInstrumento);
        if (instrumento == null) {
            throw new InstrumentoNoEncontradoException("El instrumento con nombre " + nombreInstrumento + " no se encuentra registrado");
        }
        return instrumento;
    }

    public void consultarInstrumentos(){
        instrumentoRepository.consultarInstrumentos();
    }

    public void verificarDuplicado(String nombreInstrumento) {
        if(instrumentoRepository.verificarInstrumentoDuplicado(nombreInstrumento)){
            throw new InstrumentoDuplicadoException("El instrumento con nombre " + nombreInstrumento + " ya se encuentra registrado");
        }
    }

    public void registrarInstrumento(InstrumentoFinanciero instrumento) {
        instrumentoRepository.registrarInstrumento(instrumento);
    }


    //EDICION DE INSTRUMENTOS

    /*public void modificarNombre(InstrumentoFinanciero instrumento) {
        System.out.print("Introduce el nuevo nombre: ");
        String nuevoNombre = scanner.nextLine();
        instrumento.modificarNombre(nuevoNombre);
        System.out.println("Nombre actualizado exitosamente.");
    }

    public void modificarPrecio(InstrumentoFinanciero instrumento) {
        System.out.print("Introduce el nuevo precio: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Por favor, introduce un número válido.");
            scanner.next();  // Descartar entrada incorrecta
        }
        double nuevoPrecio = scanner.nextDouble();
        instrumento.modificarPrecio(nuevoPrecio);
        System.out.println("Precio actualizado exitosamente.");
    }

    public void modificarDividendo(Accion accion) {
        System.out.print("Introduce el nuevo dividendo: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Por favor, introduce un número válido.");
            scanner.next();  // Descartar entrada incorrecta
        }
        double nuevoDividendo = scanner.nextDouble();
        accion.modificarDividendo(nuevoDividendo);
        System.out.println("Dividendo actualizado exitosamente.");
    }

    public void modificarTasaInteres(Bono bono) {
        System.out.print("Introduce la nueva tasa de interés: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Por favor, introduce un número válido.");
            scanner.next();  // Descartar entrada incorrecta
        }
        double nuevaTasaInteres = scanner.nextDouble();
        bono.modificarTasaInteres(nuevaTasaInteres);
        System.out.println("Tasa de interés actualizada exitosamente.");
    }

    public void eliminarInstrumentoPorNombre(String nombreInstrumento) {
        instrumentoRepository.eliminarInstrumentoPorNombre(nombreInstrumento);
    }
    */
    //////////////////////////

}
