import java.util.Scanner;

public class AccionFactory extends InstrumentoFactory {
    private static Scanner scanner = new Scanner(System.in);

    @Override
    public InstrumentoFinanciero crearInstrumentoConcreto(String nombre, double precio) {
        System.out.print("Ingrese el dividendo de la acción: ");
        double dividendo = scanner.nextDouble();
        scanner.nextLine();  // Limpiar el buffer
        return new Accion(nombre, precio, dividendo);
    }
}
