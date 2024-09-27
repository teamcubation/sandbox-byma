package springBootProject.java.com.example.proyectoSpringBoot.service.factory;

import springBootProject.java.com.example.proyectoSpringBoot.excepciones.customExcepcions.OpcionInvalidaException;
import springBootProject.java.com.example.proyectoSpringBoot.model.Accion;
import springBootProject.java.com.example.proyectoSpringBoot.model.Bono;
import springBootProject.java.com.example.proyectoSpringBoot.model.InstrumentoFinanciero;
import springBootProject.java.com.example.proyectoSpringBoot.service.factory.enums.TiposInstrumentosFinancieros;

public class InstrumentoFinancieroFactory  {
    public static InstrumentoFinanciero crearInstrumentoFinanciero(Integer tipo) throws OpcionInvalidaException {

        TiposInstrumentosFinancieros tipoInstrumentoFinanciero = TiposInstrumentosFinancieros.opcionSeleccionada(tipo);

        return switch (tipoInstrumentoFinanciero) {
            case BONO -> new Bono();
            case ACCION -> new Accion();
        };
    }
}
