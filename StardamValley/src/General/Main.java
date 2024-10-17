package General;

import Utils.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main {


    public static void menuInicio(){

        boolean existe=false;

        System.out.println("BINEVENIDO A STARDAM VALLEY" +
                "\n--------------------------------" +
                "\n1. NUEVA PARTIDA");

        if(existe){
            System.out.println("2. CARGAR PARTIDA");
        }

    }

    public static void menuFuncionalidades(){
        System.out.println("STARDAM VALLEY" +
                "\n---------------------------------------" +
                "\n1. INICIAR NUEVO DIA" +
                "\n2. ATENDER CULTIVOS" +
                "\n3. PLANTAR CULTIVOS EN COLUMNA" +
                "\n4. VENDER COSECHA" +
                "\n5. MOSTRAR INFORMACION DE LA GRANJA" +
                "\n6. SALIR");
    }

    public static void nuevaPartida(){
        //Procedimiento: Elimino archivos anteriores -> Opcion de cambiar properties -> Creo archivo huerto
    }

    public static void cargarPartida(){
        //Procedimiento: Inicio un nuevo dia, o sigo por el dia en el que lo dejo?
    }

    public static void main(String[] args) {

        Map<String, ArrayList<Semilla>> semillas = new HashMap<>();

        XMLFile.cargarSemillas(semillas);
    }
}