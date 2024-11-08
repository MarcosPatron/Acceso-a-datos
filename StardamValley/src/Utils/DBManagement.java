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

    /**
     * Inicializa la base de datos desde el archivo StardamValley.sql
     */
    public static void inicializarDB(){

        DBManagement.getInstance();

        deleteTable("historialconsumo");
        deleteTable("historialproduccion");
        deleteTable("transacciones");

        inicializarAlimentos();
        inicializarProductos();

    }

    public static void inicializarAlimentos(){

        DBManagement.getInstance();

        try{
            PreparedStatement stmt = connection.prepareStatement("UPDATE alimentos SET cantidad_disponible = 5 WHERE id = ?");

            for (int i = 1; i <= tamanoTabla("alimentos"); i++) {
                stmt.setInt(1, i);
                stmt.executeUpdate();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void inicializarProductos(){

        DBManagement.getInstance();

        try{
            PreparedStatement stmt = connection.prepareStatement("UPDATE productos SET cantidad_disponible = 0 WHERE id = ?");

            for (int i = 1; i <= tamanoTabla("productos"); i++) {
                stmt.setInt(1, i);
                stmt.executeUpdate();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteTable(String nombre){

        DBManagement.getInstance();

        try{
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM " + nombre);
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Toma un producto por el id
     *
     * @return el Producto almacenado en la base de datos
     */
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

    /**
     * Toma un alimeto por el id
     * @return el Alimento almacenado en la base de datos.
     */
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

    /**
     * Carga la tabla animales de la base de datos a un ArrayList
     */
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

    /**
     * @return El tamaño de la tabla introducida por paametros.
     */
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

    /**
     * Pone la cantidad de objetos almacenada a la introducida por parametros.
     */
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

    /**
     * @return La cantidad de objetos de la tabla
     */
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

    /**
     * Pone los valores introdducidos por parametros a la tabla historial
     */
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

    public static void anadirTransaccion(String tipo_transaccion, String tipo_elemento, double precio){

        DBManagement.getInstance();

        LocalDateTime fechaHora = LocalDateTime.now();

        try{
            String query = "INSERT INTO transacciones (id, tipo_transaccion, tipo_elemento, precio, fecha_transaccion) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setInt(1, (tamanoTabla("transacciones") + 1));
            stmt.setString(2,  tipo_transaccion);
            stmt.setString(3, tipo_elemento);
            stmt.setDouble(4, precio);
            stmt.setTimestamp(5, Timestamp.valueOf(fechaHora));

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
