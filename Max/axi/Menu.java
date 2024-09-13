import java.security.spec.ECField;
import java.util.Scanner;

public class Menu {
    private static Menu menu;
    private Broker broker;
    Scanner scanner;


    private Menu() {
        this.broker = Broker.getBroker();
        this.scanner = new Scanner(System.in);
    }

    public static Menu getMenu() {
        if (menu == null)
            menu = new Menu();
        return menu;
    }


    public void abrirMenu() {
        System.out.println("Ingrese la operacion que desea hacer: " +
                "1. Registrar instrumento " +
                "2. Eliminar instrumento " +
                "3. Consultar instrumentos " +
                "4. Modificar instrumento ");
        String operacion = scanner.nextLine();
        while (operacion.equals("1") || operacion.equals("2") || operacion.equals("3") ||
                operacion.equals("4")) {
            switch (operacion) {
                case "1":
                    try {
                        this.registrarInstrumento();
                    } catch (ArithmeticException n) {
                        System.out.println(n.getMessage());
                    } catch (NullPointerException s) {
                        System.out.println(s.getMessage());
                    } catch (IllegalArgumentException l) {
                        System.out.println(l.getMessage());
                    } catch (Exception a) {
                        System.out.println(a.getMessage());
                    }

                    break;
                case "2":
                    try {
                        this.eliminarInstrumento();
                    } catch (ArithmeticException n) {
                        System.out.println(n.getMessage());
                    } catch (NullPointerException s) {
                        System.out.println(s.getMessage());
                    } catch (IllegalArgumentException l) {
                        System.out.println(l.getMessage());
                    } catch (Exception a) {
                        System.out.println(a.getMessage());
                    }
                    break;
                case "3":
                    try {
                        this.consultarInstrumentos();
                    } catch (ArithmeticException n) {
                        System.out.println(n.getMessage());
                    } catch (NullPointerException s) {
                        System.out.println(s.getMessage());
                    } catch (IllegalArgumentException l) {
                        System.out.println(l.getMessage());
                    } catch (Exception a) {
                        System.out.println(a.getMessage());
                    }
                    break;
                case "4":
                    try {
                        this.modificarInstrumentos();
                    } catch (ArithmeticException n) {
                        System.out.println(n.getMessage());
                    } catch (NullPointerException s) {
                        System.out.println(s.getMessage());
                    } catch (IllegalArgumentException l) {
                        System.out.println(l.getMessage());
                    } catch (Exception a) {
                        System.out.println(a.getMessage());
                    }
                    break;
            }
            System.out.println("Ingrese la operacion que desea hacer: " +
                    "1. Registrar instrumento " +
                    "2. Eliminar instrumento " +
                    "3. Consultar instrumentos " +
                    "4. Modificar instrumento ");
            operacion = scanner.next();
        }
    }

    private void modificarInstrumentos() {
        String modificacion;
        String nombre;
        System.out.println("ingrese que desea modificar: " +
                " 1. Nombre" +
                " 2. Tipo" +
                " 3. Precio");
        String variable = scanner.next();
        switch (variable) {
            case "1":
                modificacion = this.ingresarNombre();
                break;
            case "2":
                System.out.println("ingrese el nuevo tipo de instrumento 1.Accion 2.Bono");
                modificacion = scanner.next();
                break;
            case "3":
                modificacion = String.valueOf(this.ingresarPrecio());
                break;
            default:
                throw new IllegalArgumentException("Error. Numero ingresado no valido");
        }
        System.out.println("ingrese el nombre del instrumento que desea modificar");
        nombre = scanner.next();
        broker.modificar(variable, modificacion, nombre);
    }

    private void consultarInstrumentos() {
        broker.consultar();
    }

    private void eliminarInstrumento() {
        System.out.println("ingrese el instrumento que desea eliminar");
            broker.eliminar(scanner.next());
    }

    private void registrarInstrumento() {
        Tipo t = this.ingresarTipo();
        String nombre = this.ingresarNombre();
        double precio = this.ingresarPrecio();
        broker.registrarInstrumento(nombre, precio, t);

    }

    private double ingresarPrecio() {
        System.out.println("ingrese el precio de la accion");
        double precio = scanner.nextDouble();
        return precio;
    }

    private String ingresarNombre() {
        System.out.println("ingrese el nombre de la accion");
        String nombre = scanner.next();
        return nombre;
    }

    private Tipo ingresarTipo() {
        System.out.println("ingrese que tipo de instrumento quiere ingresar: 1.Accion, 2.Bono");
        String tipo = scanner.next();
        Tipo t;
        switch (tipo) {
            case "1":
                t = Tipo.ACCION;
                break;
            case "2":
                t = Tipo.BONO;
                break;
            default:
                t = null;
        }
        return t;
    }
}
