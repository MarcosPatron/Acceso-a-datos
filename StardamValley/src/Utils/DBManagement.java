package Utils;

import Establo.Tipo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBManagement {

    private static DBManagement instance;
    private static Connection connection;

    private DBManagement(){

        String db_url = PropertiesF.tomarValor("url", Constants.DB_PROPERTIES);
        String db_user = PropertiesF.tomarValor("user", Constants.DB_PROPERTIES);
        String db_pass = PropertiesF.tomarValor("password", Constants.DB_PROPERTIES);

        try{

            System.out.println("Cargando...");
            Class.forName(PropertiesF.tomarValor("driver", Constants.DB_PROPERTIES));
            connection = DriverManager.getConnection(db_url, db_user, db_pass);
            System.out.println("OK");


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static DBManagement getInstance(){

        if(instance == null){
            instance = new DBManagement();
        }
        return instance;
    }

    public static void cargarDB(){
        DBManagement.getInstance();

        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM producto");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                Tipo tipo = Tipo.valueOf(rs.getString("tipo"));
                String nombre = rs.getString("nombre");
                double peso = rs.getDouble("peso");
                //Falta idAlimento e idProducto

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
