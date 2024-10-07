package  springbootApp.app.models;

public class InstrumentoFactory {

    public static final String TIPO_INVALIDO = "Error. Tipo invalido";
    public static final String BONO = "BONO";
    public static final String ACCION = "ACCION";

    public static InstrumentoFinanciero nuevoInstrumento(String nombre, double precio, String tipo) {
        InstrumentoFinanciero instrumentoFinanciero;
        tipo = tipo.toUpperCase();
        instrumentoFinanciero = switch (tipo) {
            case ACCION -> Accion.builder()
                    .nombre(nombre)
                    .precio(precio)
                    .tipo(Tipo.ACCION)
                    .build();
            case BONO -> Bono.builder()
                    .nombre(nombre)
                    .precio(precio)
                    .tipo(Tipo.BONO)
                    .build();
            default -> throw new IllegalArgumentException(TIPO_INVALIDO);
        };
        return instrumentoFinanciero;
    }
}