package  springbootApp.app.models;

public class InstrumentoFactory {
    public static InstrumentoFinanciero nuevoInstrumento(String nombre, double precio, String tipo) {
        InstrumentoFinanciero instrumentoFinanciero;
        tipo = tipo.toUpperCase();
        switch (tipo) {
            case "ACCION":
                instrumentoFinanciero = new Accion(nombre, precio, Tipo.ACCION);
                break;
            case "BONO":
                instrumentoFinanciero = new Bono(nombre, precio, Tipo.BONO);
                break;
            default:
                throw new IllegalArgumentException("Error. Tipo invalido");
        }
        return instrumentoFinanciero;
    }
}
