package src.inventario;

public class PruebaActualizarStockDAO {

    public static void main(String[] args) {

        ProductoDAO productoDAO = new ProductoDAO();

        int idProducto = 1;
        int nuevoStock = 25;

        productoDAO.actualizarStock(idProducto, nuevoStock);
    }
}