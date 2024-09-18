package axi.modelos;

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
                i = null;
        }
        return i;
    }
}
