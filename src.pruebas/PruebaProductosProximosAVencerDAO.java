package src.inventario;

import java.util.ArrayList;

public class PruebaProductosProximosAVencerDAO {

    public static void main(String[] args) {

        ProductoDAO productoDAO = new ProductoDAO();

        String fechaLimite = "2026-12-31";

        ArrayList<Producto> productos = productoDAO.listarProductosProximosAVencer(fechaLimite);

        System.out.println("Productos próximos a vencer hasta la fecha: " + fechaLimite);
        System.out.println("------------------------------------------------");

        if (productos.isEmpty()) {
            System.out.println("No existen productos próximos a vencer.");
        } else {
            for (Producto producto : productos) {
                System.out.println(producto);
            }
        }
    }
}