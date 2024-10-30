import java.sql.*;

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

    public static void mostrarTienda(){

        GestionDB.getInstance();

        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM producto");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                System.out.println("Producto " + id + " --> Nombre: " + nombre + ". Precio: " + precio);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void mostrarFabicante(){
        Connection conn = establecerConexion();

        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM fabricante");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                System.out.println("Producto " + id + " --> Nombre: " + nombre);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void modificarProducto(String nombre, double precio){

        Connection conn = establecerConexion();

        try{
            String query = "UPDATE producto SET nombre = ? AND precio = ? WHERE  id = 1";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, nombre);
            stmt.setDouble(2, precio);

            int numAct = stmt.executeUpdate();
            System.out.println("Se han modificado " + numAct + " registros.");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void eliminarProducto(String nombre){

        Connection conn = establecerConexion();

        try {
            String query = "DELETE FROM producto WHERE nombre = ?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, nombre);

            int numAct = stmt.executeUpdate();
            System.out.println("Se han eliminado " + numAct + " registros.");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
