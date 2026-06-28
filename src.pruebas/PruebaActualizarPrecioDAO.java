package src.inventario;

public class PruebaActualizarPrecioDAO {

    public static void main(String[] args) {

        ProductoDAO productoDAO = new ProductoDAO();

        int idProducto = 5;
        double nuevoPrecio = 1600.00;

        productoDAO.actualizarPrecio(idProducto, nuevoPrecio);
    }
}