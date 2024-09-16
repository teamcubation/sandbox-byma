import java.util.Scanner;

public abstract class InstrumentoFactory {
    private static Scanner scanner = new Scanner(System.in);

    // Método para crear el instrumento sin preguntar por el tipo (ya se conoce)
    public InstrumentoFinanciero crearInstrumento() {
        System.out.print("Ingrese el nombre del Instrumento: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el precio del Instrumento: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();  // Limpiar el buffer

        // Llamar al método concreto de la subclase
        return crearInstrumentoConcreto(nombre, precio);
    }

    // Método abstracto para que las subclases implementen la creación del instrumento
    public abstract InstrumentoFinanciero crearInstrumentoConcreto(String nombre, double precio);
}
