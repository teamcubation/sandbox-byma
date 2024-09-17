import java.util.ArrayList;
import java.util.Scanner;

public class GestorIntrumento {
    private static GestorIntrumento gestor;
    private ArrayList<InstrumentoFinanciero> instrumentos;
    private Scanner scanner;

    private GestorIntrumento() {
        this.instrumentos = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Método para obtener la única instancia
    public static GestorIntrumento getInstance() {
        if (gestor == null) {
            gestor = new GestorIntrumento();
        }
        return gestor;
    }

    // Método para iniciar la gestión
    public void iniciarGestion() {
        boolean continuar = true;

        while (continuar) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Registrar Instrumento");
            System.out.println("2. Consultar Acciones y Bonos");
            System.out.println("3. Editar Acción o Bono");
            System.out.println("4. Eliminar Acción o Bono");
            System.out.println("5. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    registrarInstrumento();
                    break;
                case 2:
                    consultarInstrumentos();
                    break;
                case 3:
                    //editarInstrumento();
                    break;
                case 4:
                    //eliminarInstrumento();
                    break;
                case 5:
                    continuar = false;
                    System.out.println("Gracias por usar el sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    break;
            }
        }
    }

    private void registrarInstrumento() {
        // Selección del tipo de instrumento
        InstrumentoFactory factory = seleccionarFabricaInstrumento();

        if (factory != null) {
            InstrumentoFinanciero instrumento = factory.crearInstrumento();
            instrumentos.add(instrumento);  // Añadir instrumento a la lista
            System.out.println("Instrumento registrado con éxito.");
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

    private void consultarInstrumentos() {
        System.out.println("Lista de Instrumentos:");
        for (InstrumentoFinanciero instrumento : instrumentos) {
            System.out.println(instrumento);
        }
    }

    /*private void editar() {
        System.out.print("Ingrese el nombre del instrumento a editar: ");
        String nombre = scanner.nextLine();
        InstrumentoFinanciero instrumento = buscarInstrumento(nombre);
        if (instrumento != null) {
            System.out.print("Ingrese el nuevo nombre: ");
            instrumento.setNombre(scanner.nextLine());
            System.out.print("Ingrese el nuevo precio: ");
            instrumento.setPrecio(scanner.nextDouble());
            scanner.nextLine();  // Limpiar el buffer
            System.out.println("Instrumento editado exitosamente.");
        } else {
            System.out.println("Instrumento no encontrado.");
        }
    }

    private void eliminar() {
        System.out.print("Ingrese el nombre del instrumento a eliminar: ");
        String nombre = scanner.nextLine();
        InstrumentoFinanciero instrumento = buscarInstrumento(nombre);
        if (instrumento != null) {
            instrumentos.remove(instrumento);
            System.out.println("Instrumento eliminado exitosamente.");
        } else {
            System.out.println("Instrumento no encontrado.");
        }
    }

    private InstrumentoFinanciero buscarInstrumento(String nombre) {
        for (InstrumentoFinanciero instrumento : instrumentos) {
            if (instrumento.getNombre().equalsIgnoreCase(nombre)) {
                return instrumento;
            }
        }
        return null;
    }*/
}
