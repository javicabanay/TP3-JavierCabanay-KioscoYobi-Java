package src.inventario;

import java.util.ArrayList;
import java.util.Comparator;

/*
 * Clase Inventario.
 *
 * Se encarga de administrar los productos del sistema.
 * Utiliza una lista dinámica ArrayList para almacenar objetos Producto.
 */
public class Inventario {

    // Lista donde se guardan los productos registrados
    private ArrayList<Producto> productos;

    /*
     * Constructor.
     * Inicializa la lista de productos vacía.
     */
    public Inventario() {
        productos = new ArrayList<>();
    }

    /*
     * Agrega un producto al inventario.
     */
    public void agregarProducto(Producto producto) {
        productos.add(producto);
        System.out.println("Producto registrado correctamente.");
    }

    /*
     * Lista todos los productos registrados.
     */
    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            for (Producto producto : productos) {
                System.out.println(producto);
            }
        }
    }

    /*
     * Busca un producto por nombre.
     */
    public Producto buscarProducto(String nombre) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }

        return null;
    }

    /*
     * Ordena los productos alfabéticamente por nombre.
     */
    public void ordenarPorNombre() {
        productos.sort(Comparator.comparing(Producto::getNombre));
        System.out.println("Productos ordenados por nombre correctamente.");
    }

    /*
     * Actualiza el stock de un producto existente.
     */
    public void actualizarStock(String nombre, int nuevoStock) {
        Producto producto = buscarProducto(nombre);

        if (producto != null) {
            producto.setCantidadStock(nuevoStock);
            System.out.println("Stock actualizado correctamente.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    /*
 * Actualiza el precio de un producto existente.
 */
    public void actualizarPrecio(String nombre, double nuevoPrecio) {
        Producto producto = buscarProducto(nombre);

        if (producto != null) {
            producto.setPrecio(nuevoPrecio);
            System.out.println("Precio actualizado correctamente.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    /*
     * Muestra productos próximos a vencer.
     * En esta primera versión se compara usando texto simple.
     * Más adelante podemos mejorarlo usando LocalDate.
     */
    public void detectarProximosAVencer(String fechaLimite) {
        boolean encontrado = false;

        for (Producto producto : productos) {
            if (producto.getFechaVencimiento().compareTo(fechaLimite) <= 0) {
                System.out.println(producto);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No hay productos próximos a vencer.");
        }
    }

    /*
 * Elimina un producto del inventario.
 */
     public void eliminarProducto(String nombre) {

         Producto producto = buscarProducto(nombre);

         if (producto != null) {
            productos.remove(producto);
            System.out.println("Producto eliminado correctamente.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    /*
 * Calcula el valor total del inventario.
 */
     public void mostrarValorTotalInventario() {

         double total = 0;

         for (Producto producto : productos) {

             total += producto.getCantidadStock()
                * producto.getPrecio();
        }

             System.out.println("Valor total del inventario: $" + total);
    }
}