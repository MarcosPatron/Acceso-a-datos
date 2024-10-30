import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Producto {

    private int id;
    private String nombre;
    private double precio;
    private int idFabricante;
    private String nombreFabicante;

    public Producto(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.idFabricante = -1;
        this.nombreFabicante = "";
    }

    public Producto(int id, String nombre, double precio, int idFabricante, java.lang.String nombreFabicante) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.idFabricante = idFabricante;
        this.nombreFabicante = nombreFabicante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdFabricante() {
        return idFabricante;
    }

    public void setIdFabricante(int idFabricante) {
        this.idFabricante = idFabricante;
    }

    public String getNombreFabicante() {
        return nombreFabicante;
    }

    public void setNombreFabicante(String nombreFabicante) {
        this.nombreFabicante = nombreFabicante;
    }

    public static void establecerConexion(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/tienda";
            Connection conn = DriverManager.getConnection(url, "root", "root");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void anadirDB(){


        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM producto");
            ResultSet rs = stmt.executeQuery();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
