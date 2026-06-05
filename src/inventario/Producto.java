package src.inventario;

/*
 * Clase Producto.
 *
 * Representa un producto del inventario.
 * Cada producto posee:
 * - ID
 * - Nombre
 * - Cantidad en stock
 * - Fecha de vencimiento
 * - Categoría
 */
public class Producto {

    // Identificador único del producto
    private int idProducto;

    // Nombre del producto
    private String nombre;

    // Cantidad disponible en stock
    private int cantidadStock;

    // Fecha de vencimiento del producto
    private String fechaVencimiento;

    // Categoría a la que pertenece el producto
    private Categoria categoria;

    // Precio del producto
    private double precio;

    /*
     * Constructor de la clase Producto.
     */
    public Producto(int idProducto,
                    String nombre,
                    int cantidadStock,
                    String fechaVencimiento,
                    double precio,
                    Categoria categoria) {

        this.idProducto = idProducto;
        this.nombre = nombre;
        this.cantidadStock = cantidadStock;
        this.fechaVencimiento = fechaVencimiento;
        this.precio = precio;
        this.categoria = categoria;
    }

    // Getter del ID
    public int getIdProducto() {
        return idProducto;
    }

    // Setter del ID
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    // Getter del nombre
    public String getNombre() {
        return nombre;
    }

    // Setter del nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter del stock
    public int getCantidadStock() {
        return cantidadStock;
    }

    // Setter del stock
    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    // Getter de la fecha de vencimiento
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    // Setter de la fecha de vencimiento
    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    //Getter del precio
    public double getPrecio() {
    return precio;
    }

    //Setter del precio
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Getter de la categoría
    public Categoria getCategoria() {
        return categoria;
    }

    // Setter de la categoría
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /*
     * Muestra la información completa del producto.
     */
    @Override
    public String toString() {

    return "ID: " + idProducto +
            " | Nombre: " + nombre +
            " | Stock: " + cantidadStock +
            " | Precio: $" + precio +
            " | Vencimiento: " + fechaVencimiento +
            " | Categoria: " + categoria.getNombreCategoria();
}
}