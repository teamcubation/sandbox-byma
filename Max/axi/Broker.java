import java.util.ArrayList;
import java.util.Optional;

public class Broker {
    private static Broker broker;
    private String name;
    private ArrayList<InstrumentoFinanciero> instrumentos;

    private Broker() {
        this.name = "axi";
        this.instrumentos = new ArrayList<>();
    }

    public static Broker getBroker() {
        if (broker == null)
            broker = new Broker();
        return broker;
    }

    public void registrarInstrumento(String nombre, double precio, Tipo tipo) {
        if (nombre == null || nombre.isEmpty())
            throw new NullPointerException("nombre invalido");
        if (precio < 0)
            throw new ArithmeticException("el precio no puede ser menor a cero");
        InstrumentoFinanciero i = buscarInstrumento(nombre);
        if (i != null) {
            throw new IllegalArgumentException("accion existente");
        } else if (tipo.equals(Tipo.BONO)) {
            instrumentos.add(new Bono(nombre, precio, tipo));
        } else if (tipo.equals(Tipo.ACCION))
            instrumentos.add(new Accion(nombre, precio, tipo));
        else
            throw new IllegalArgumentException("Tipo inexistente");
    }

    private InstrumentoFinanciero buscarInstrumento(String nombre) {
        InstrumentoFinanciero i = null;
        for (int j = 0; j < instrumentos.size(); j++) {
            if (instrumentos.get(j).getNombre().equals(nombre))
                i = instrumentos.get(j);
        }
        return i;
    }


    public void consultar() {
        for (InstrumentoFinanciero instrumento : instrumentos) {
            System.out.println(instrumento);
        }
    }

    public void modificar(String variable, String modificacion, String nombre) {
        InstrumentoFinanciero i = this.buscarInstrumento(nombre);
        if (i != null) {
            switch (variable) {
                case "1":
                    if (modificacion == null || modificacion.isEmpty())
                        throw new NullPointerException("Error. El nombre no puede ser nulo ni vacio");
                    i.setNombre(modificacion);
                    break;
                case "2":
                    if (modificacion.equals("1")) {
                        i.setTipo(Tipo.ACCION);
                    } else if (modificacion.equals("2")) {
                        i.setTipo(Tipo.BONO);
                    } else {
                        throw new IllegalArgumentException("Error. Tipo incorrecto");
                    }
                    break;
                case "3":
                    try {
                        double numero = Double.parseDouble(modificacion);
                        i.setPrecio(numero);
                    } catch (NumberFormatException e) {
                        System.out.println("Error de formato: ingrese un número.");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Error. los campos no coinciden");
            }
        }
    }

    public void eliminar(String nombre) {
        InstrumentoFinanciero i = instrumentos.stream()
                .filter(e -> e.getNombre().equals(nombre))
                .findFirst().orElse(null);
        if (i == null) {
            throw new NullPointerException("Error. Instrumento no encontrado");
        }
        instrumentos.remove(i);
    }
}
