package axi;

public class InstrumentoFinancieroFactory {

    public static InstrumentoFinanciero nuevoInstrumento(String nombre, double precio, Tipo tipo) {

        InstrumentoFinanciero i = null;
        if (tipo.equals(Tipo.ACCION)) {
            i = new Accion(nombre, precio);
        }
        if (tipo.equals(Tipo.BONO)) {
            i = new Bono(nombre, precio);
        }
        return i;
    }
}
