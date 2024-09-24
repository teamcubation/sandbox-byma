package springbootApp.java.models;

public class InstrumentoFactory {
    public static InstrumentoFinanciero nuevoInstrumento(String nombre, double precio, Tipo tipo) {
        InstrumentoFinanciero instrumentoFinanciero;
        switch (tipo) {
            case Tipo.ACCION:
                instrumentoFinanciero = new Accion(nombre, precio);
                break;
            case Tipo.BONO:
                instrumentoFinanciero = new Bono(nombre, precio);
                break;
            default:
                throw new IllegalArgumentException("Error. Tipo invalido");
        }
        return instrumentoFinanciero;
    }
}
