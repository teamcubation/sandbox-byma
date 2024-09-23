package consoleApp.models;

import java.util.Scanner;

public class BonoFactory extends InstrumentoFactory {
    private static Scanner scanner = new Scanner(System.in);

    @Override
    public InstrumentoFinanciero crearInstrumentoConcreto(String nombre, double precio) {
        System.out.print("Ingrese la tasa de interés del bono: ");
        double tasaInteres = scanner.nextDouble();
        scanner.nextLine();  // Limpiar el buffer
        return new Bono(nombre, precio, tasaInteres);
    }
}

