import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        boolean continuar = true;

        while (continuar){

        }

        try {
            System.out.println("Ingresar la operacion....");

            Scanner scanner = new Scanner(System.in);

            String inputDeOperacion = scanner.nextLine();

            //Logica de la opreacion



            //
            System.out.println("Desea realizar otra operacion");
            int opcion = scanner.nextInt();
            System.out.println("Ingrese 1 para continuar con los calculos y 2 para salir.");

            switch (opcion){
                case 1:

                    break;
                case 2:
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
