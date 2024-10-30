import java.util.Scanner;

public class Main {

    public static String elegir(){
        Scanner ent = new Scanner(System.in);
        return ent.nextLine();
    }

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
                    Producto.mostrarTienda();
                    break;
                case 2:
                    Producto.mostrarFabicante();
                    break;
                case 3:
                    System.out.println("Dame el nombre y el precio del producto:");
                    Producto.modificarProducto(elegir(), Double.parseDouble(elegir()));
                    break;
                case 4:
                    System.out.println("Dame el nombre del producto que quires eliminar:");
                    Producto.eliminarProducto(elegir());
                    break;
                case 5:
                    salir = true;
                    break;
            }
        }while(!salir);



    }
}