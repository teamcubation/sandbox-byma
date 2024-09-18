package models;

import exceptions.InstrumentoDuplicadoException;
import repositories.InstrumentoRepository;

import java.util.Scanner;

public abstract class InstrumentoFactory {
    private static Scanner scanner = new Scanner(System.in);

    // Método para crear el instrumento sin preguntar por el tipo (ya se conoce) recibo el repository por parametro porque si creo otro aca se pierden los datos ya que seria otra instancia
    public InstrumentoFinanciero crearInstrumento(InstrumentoRepository instrumentoRepository) throws InstrumentoDuplicadoException {
        System.out.print("Ingrese el nombre del Instrumento: ");
        String nombre = scanner.nextLine();

        InstrumentoFinanciero instrumentoEncontrado = buscarInstrumentoPorNombre(instrumentoRepository, nombre);

        if (instrumentoEncontrado != null) {
            throw new InstrumentoDuplicadoException("El instrumento con el nombre que ha intentado ingresar ya esta registrado");
        }

        System.out.print("Ingrese el precio del Instrumento: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();  // Limpiar el buffer

        // Llamar al método concreto de la subclase
        return crearInstrumentoConcreto(nombre, precio);
    }

    //
    private InstrumentoFinanciero buscarInstrumentoPorNombre(InstrumentoRepository instrumentoRepository, String nombre) {
        return instrumentoRepository.buscarInstrumentoPorNombre(nombre);
    }

    // Método abstracto para que las subclases implementen la creación del instrumento
    public abstract InstrumentoFinanciero crearInstrumentoConcreto(String nombre, double precio);
}
