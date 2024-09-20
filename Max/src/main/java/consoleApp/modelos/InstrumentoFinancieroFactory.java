package consoleApp.modelos;

public class InstrumentoFinancieroFactory {

    public static InstrumentoFinanciero nuevoInstrumento(String nombre, double precio, Tipo tipo) {
        InstrumentoFinanciero i;
        switch (tipo) {
            case Tipo.ACCION:
                i = new Accion(nombre, precio);
                break;
            case Tipo.BONO:
                i = new Bono(nombre, precio);
                break;
            default:
                throw new IllegalArgumentException("Error. Tipo invalido");
        }
        return i;
    }
}
