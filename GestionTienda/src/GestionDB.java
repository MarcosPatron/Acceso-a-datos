import java.sql.Connection;
import java.sql.DriverManager;

public class GestionDB {

    private final String DB_URL = "jdbc:mysql://localhost:3306/tienda";
    private final String DB_USER = "root";
    private final String DB_PASS = "root";

    private static GestionDB instance;
    private Connection connection;

    private GestionDB(){
        try{

            System.out.println("Cargando...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("OK");


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static GestionDB getInstance(){

        if(instance == null){
            instance = new GestionDB();
        }
        return instance;
    }
}
