import java.util.ArrayList;

public class Inversor implements Observer{
    private String nombre;
    private ArrayList <InstrumentoFinanciero>cartera;

    public Inversor(String nombre) {
        this.nombre = nombre;
        cartera = new ArrayList<>();
    }

    public void suscribirse(InstrumentoFinanciero i){
        cartera.add(i);
    }
    public void desuscribirse(InstrumentoFinanciero i){
        cartera.remove(i);
    }

    @Override
    public void actualizar(double precio, String name) {

        System.out.println("el precio del instrumento "+ name + "cambio a " + precio);
    }
}