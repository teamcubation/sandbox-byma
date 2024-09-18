package services;

import exceptions.InstrumentoDuplicadoException;
import exceptions.InstrumentoNoEncontradoException;
import models.*;
import repositories.InstrumentoRepository;

import java.util.Scanner;

public class InstrumentoService {

    Scanner scanner = new Scanner(System.in);
    InstrumentoRepository instrumentoRepository = new InstrumentoRepository();

    public void registrarInstrumento() throws IllegalArgumentException {

        // Selección del tipo de instrumento
        InstrumentoFactory factory = seleccionarFabricaInstrumento();

        if (factory != null) {
            try {
                InstrumentoFinanciero instrumento = factory.crearInstrumento(instrumentoRepository);
                instrumentoRepository.registrarInstrumento(instrumento);
                System.out.println("Instrumento registrado con éxito.");
            } catch (InstrumentoDuplicadoException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("No se pudo registrar el instrumento.");
        }
    }

    private InstrumentoFactory seleccionarFabricaInstrumento() {
        System.out.println("Seleccione el tipo de instrumento a registrar:");
        System.out.println("1. Acción");
        System.out.println("2. Bono");

        int opcion = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer

        switch (opcion) {
            case 1:
                return new AccionFactory();  // Devuelve la fábrica de Acciones
            case 2:
                return new BonoFactory();  // Devuelve la fábrica de Bonos
            default:
                System.out.println("Opción no válida. Inténtelo de nuevo.");
                return null;
        }
    }

    public InstrumentoFinanciero buscarInstrumentoPorNombre(String nombreInstrumento) {
        return instrumentoRepository.buscarInstrumentoPorNombre(nombreInstrumento);
    }

    public void consultarInstrumentos() throws InstrumentoNoEncontradoException {

        instrumentoRepository.consultarInstrumentos();

        System.out.println("¿Desea consultar los detalles de un instrumento específico? (si/no)");


        String respuesta = scanner.nextLine();
        System.out.println("Respuesta leída: '" + respuesta + "'"); //ARREGLAR BUG

        if (respuesta.equalsIgnoreCase("si")) {
            System.out.print("Introduce el nombre del instrumento: ");
            String nombreInstrumento = scanner.nextLine();
            InstrumentoFinanciero instrumento = buscarInstrumentoPorNombre(nombreInstrumento);

            if (instrumento != null) {
                System.out.println("Detalles del instrumento:");
                System.out.println(instrumento);
            } else {
                throw new InstrumentoNoEncontradoException("El instrumento con nombre " + nombreInstrumento + " no se encuentra registrado");
            }
        } else {
            System.out.println("Consulta finalizada.");
        }


    }



    //EDICION DE INSTRUMENTOS

    public void modificarNombre(InstrumentoFinanciero instrumento) {
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

    //////////////////////////

}
