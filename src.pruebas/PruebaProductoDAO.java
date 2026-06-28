package src.inventario;

import java.util.ArrayList;

public class PruebaProductoDAO {

    public static void main(String[] args) {

        ProductoDAO productoDAO = new ProductoDAO();

        ArrayList<Producto> productos = productoDAO.listarProductos();

        System.out.println("Listado de productos desde MySQL:");
        System.out.println("--------------------------------");

        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }
}