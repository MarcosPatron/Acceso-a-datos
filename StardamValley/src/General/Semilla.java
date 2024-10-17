package General;

public class Semilla {

    private String id;
    private String nombre;
    private String estacion;
    private int diasCrecimiento;
    private int precioCompra;
    private int precioVenta;
    private int maxFrutos;

    public Semilla(String id, String nombre, String estacion, int diasCrecimiento, int precioCompra, int precioVenta, int maxFrutos) {
        this.id = id;
        this.nombre = nombre;
        this.estacion = estacion;
        this.diasCrecimiento = diasCrecimiento;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.maxFrutos = maxFrutos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstacion() {
        return estacion;
    }

    public void setEstacion(String estacion) {
        this.estacion = estacion;
    }

    public int getDiasCrecimiento() {
        return diasCrecimiento;
    }

    public void setDiasCrecimiento(int diasCrecimiento) {
        this.diasCrecimiento = diasCrecimiento;
    }

    public int getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(int precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(int precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getMaxFrutos() {
        return maxFrutos;
    }

    public void setMaxFrutos(int maxFrutos) {
        this.maxFrutos = maxFrutos;
    }
}
