package consoleApp.models;

public class Inversor implements Observer {
    private String nombre;

    public Inversor(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void actualizar(InstrumentoFinanciero instrumento) {
        System.out.println("Inversor " + nombre + " ha sido notificado de un cambio en: "
                + instrumento.getNombre() + ". Nuevo precio: " + instrumento.getPrecio());
    }
}
