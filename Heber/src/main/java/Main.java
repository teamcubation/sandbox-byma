
import contenedorBoot.ContenedorBoot;
import controllers.InstrumentoController;
import repositories.InstrumentoRepository;
import services.InstrumentoService;


public class Main {
    public static void main(String[] args) {

        InstrumentoController instrumentoController = InstrumentoController.getInstance();
        instrumentoController.iniciarGestion();
    }
}