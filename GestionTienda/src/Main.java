import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        boolean salir = false;
        Scanner ent = new Scanner(System.in);

        do{
            System.out.println("-----TIENDA-----" +
                    "\n 1. Mostrar productos tienda." +
                    "\n 2. Mostrar productos fabricante." +
                    "\n 3. Modificar datos de un producto." +
                    "\n 4. Eliminar producto." +
                    "\n 5. Salir." );

            switch(ent.nextInt()){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    salir = true;
                    break;
            }
        }while(!salir);



    }
}