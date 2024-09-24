package consoleApp.models;

import springApp.java.com.example.demo.exceptions.InstrumentoDuplicadoException;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class InstrumentoFactory {
    private static Scanner scanner = new Scanner(System.in);

    // Método para crear el instrumento sin preguntar por el tipo (ya se conoce) recibo el repository por parametro porque si creo otro aca se pierden los datos ya que seria otra instancia
    public InstrumentoFinanciero crearInstrumento(String nombreInstrumento) throws InstrumentoDuplicadoException {

        // no se si esta bien pedir el precio aca o deberia pedirlo en el metodo del controller en el que estoy enviando el nombre
        double precio = 0;
        boolean precioValido = false;

        //agrego validacion por si el usuario ingresa un string cuando se espera un numero
        while (!precioValido) {
            try {
                System.out.print("Ingrese el precio del Instrumento: ");
                precio = scanner.nextDouble();
                scanner.nextLine();  // Limpiar el buffer
                precioValido = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                scanner.nextLine();  // Limpiar el buffer después del error
            }
        }

        // Llamar al método concreto de la subclase
        return crearInstrumentoConcreto(nombreInstrumento, precio);
    }

    // Método abstracto para que las subclases implementen la creación del instrumento
    public abstract InstrumentoFinanciero crearInstrumentoConcreto(String nombreInstrumento, double precio);
}
