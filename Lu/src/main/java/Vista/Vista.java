package Vista;

import Modelo.InstrumentoFinanciero;

import java.util.List;
import java.util.Scanner;

public class Vista {

    private final Scanner scanner;

    public Vista() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.print("\n" + """
                Selecciona la opción que queres realizar: \
                
                1. Registrar\
                
                2. Consultar\
                
                3. Editar - Vas a tener que ingresar el nombre del instrumento financiero a editar\
                
                4. Eliminar - Vas a tener que ingresar el nombre del instrumento financiero a eliminar\
                
                5. Salir""" + "\n");
    }

    public int opcionUsuario() {
        return Integer.parseInt(scanner.nextLine());
    }

    public String getNombre() {
        System.out.print("Ingresa el nombre: ");
        return scanner.nextLine();
    }

    public int getTipo() {
        System.out.print("Selecciona el instrumento financiero que queres agregar: \n1. Bono\n2. Accion\n");
        return Integer.parseInt(scanner.nextLine());
    }

    public double getPrecio() {
        System.out.print("Ingresa el precio: ");
        return Double.parseDouble(scanner.nextLine());
    }

    public void mensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void consultarOpciones() {
        System.out.print("""
                Selecciona la opción que queres realizar: \
                
                1. Consultar un intrumento financiero - Vas a tener que ingresar el nombre del instrumento financiero a consultar\
                
                2. Consultar todos mis intrumentos""" + "\n");
    }

    public void consultar(InstrumentoFinanciero instrumentoFinanciero) {
        System.out.println(instrumentoFinanciero.toString());
    }

    public void consultar(List<InstrumentoFinanciero> instrumentosFinancieros) {
        if (instrumentosFinancieros.isEmpty()) {
            System.out.print("\n-------- No tiene ningún instrumento financiero registrado --------\n\n");
        }

        for (InstrumentoFinanciero instrumento : instrumentosFinancieros) {
            System.out.println(instrumento.toString());
        }
    }

    public void editarOpciones() {
        System.out.print("""
                Selecciona el atributo que queres editar del instrumento financiero: \
                
                1. Nombre\
                
                2. Precio"""+ "\n");
    }

    public void salir() {
        scanner.close();
        mensaje("¡Gracias por usar el gestor de instrumentos financieros!");
    }
}
