package src.inventario;

public class PruebaEliminarProductoDAO {

    public static void main(String[] args) {

        ProductoDAO productoDAO = new ProductoDAO();

        int idProducto = 5;

        productoDAO.eliminarProducto(idProducto);
    }
}