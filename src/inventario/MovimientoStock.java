package src.inventario;

/*
 * Clase MovimientoStock.
 *
 * Representa una entrada o salida de mercadería.
 * Por ejemplo:
 * - Entrada: cuando se repone stock.
 * - Salida: cuando se vende un producto.
 */
public class MovimientoStock {

    private int idMovimiento;
    private String tipoMovimiento;
    private int cantidad;
    private String fechaMovimiento;
    private String descripcion;
    private Producto producto;
    private Usuario usuario;

    /*
     * Constructor.
     * Inicializa un movimiento de stock con todos sus datos.
     */
    public MovimientoStock(int idMovimiento,
                           String tipoMovimiento,
                           int cantidad,
                           String fechaMovimiento,
                           String descripcion,
                           Producto producto,
                           Usuario usuario) {

        this.idMovimiento = idMovimiento;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.fechaMovimiento = fechaMovimiento;
        this.descripcion = descripcion;
        this.producto = producto;
        this.usuario = usuario;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(String fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /*
     * Método toString().
     * Permite mostrar el movimiento de forma ordenada.
     */
    @Override
    public String toString() {
        return "ID Movimiento: " + idMovimiento +
                " | Tipo: " + tipoMovimiento +
                " | Cantidad: " + cantidad +
                " | Fecha: " + fechaMovimiento +
                " | Producto: " + producto.getNombre() +
                " | Usuario: " + usuario.getNombre() +
                " | Descripción: " + descripcion;
    }
}