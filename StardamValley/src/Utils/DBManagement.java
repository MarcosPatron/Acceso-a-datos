package Utils;

import Establo.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

    public static Producto cargarProducto(int id){

        DBManagement.getInstance();

        Producto p = null;
        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM productos WHERE id = " + id);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");

                p = new Producto(nombre, precio);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return p;
    }
    public static Alimento cargarAlimento(int id){

        DBManagement.getInstance();

        Alimento a = null;
        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM alimentos WHERE id = " + id);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");

                a = new Alimento(nombre, precio);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return a;
    }

    public static void cargarDB(ArrayList<Animal> animales){

        DBManagement.getInstance();

        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM animales");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                Tipo tipo = Tipo.valueOf(rs.getString("tipo"));
                String nombre = rs.getString("nombre");
                double peso = rs.getDouble("peso");
                Alimento a = cargarAlimento(rs.getInt("id_alimento"));
                Producto p = cargarProducto(rs.getInt("id_producto"));

                switch (tipo){
                    case VACA -> animales.add(new Vaca(id, tipo, nombre, a, p, peso));
                    case CERDO -> animales.add(new Cerdo(id, tipo, nombre, a, p));
                    case OVEJA -> animales.add(new Oveja(id, tipo, nombre, a, p));
                    case GALLINA -> animales.add(new Gallina(id, tipo, nombre, a, p));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int tamanoTabla(String nombre){

        DBManagement.getInstance();
        int tam;

        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) AS tam FROM " + nombre);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
                tam = rs.getInt("tam");
            else{
                tam = 0;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return tam;
    }

    public static void setCantidadDB(String nombre, int cantidad, String nombreA){

        DBManagement.getInstance();

        try{
            String query = "UPDATE " + nombre + " SET cantidad_disponible = ? WHERE nombre = ?";
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setInt(1, cantidad);
            stmt.setString(2, nombreA);

            stmt.executeUpdate();;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int getCantidadDB(String nombre, String nombreA){

        int cantidad = 0;

        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT cantidad_disponible FROM " + nombre
                                                                + " WHERE nombre = ?");

            stmt.setString(1, nombreA);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                cantidad = rs.getInt("cantidad_disponible");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return cantidad;
    }

    public static void tablaHistorial(String tabla, int idAnimal, int cantidad) {
        DBManagement.getInstance();
        LocalDateTime fechaHora = LocalDateTime.now();
        String cantidadT;

        try {

            if(tabla.equals("consumo")){
                cantidadT = "cantidad_consumida";
            }
            else{
                cantidadT = "cantidad";
            }

            String query = "INSERT INTO historial" + tabla + " (id, id_animal, " + cantidadT + ", fecha_" + tabla + ") VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setInt(1, tamanoTabla("historial" + tabla) + 1);
            stmt.setInt(2, idAnimal);
            stmt.setInt(3, cantidad);
            stmt.setTimestamp(4, Timestamp.valueOf(fechaHora));

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
