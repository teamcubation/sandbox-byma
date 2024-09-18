import app.GestorIntrumento;
import models.Accion;
import models.Inversor;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        GestorIntrumento gestor = GestorIntrumento.getInstance();
        gestor.iniciarGestion();
    }
}