package src.inventario;

public class PruebaRegistrarProductoDAO {

    public static void main(String[] args) {

        ProductoDAO productoDAO = new ProductoDAO();

        Categoria categoria = new Categoria(2, "Bebidas");

        Producto producto = new Producto(
            0,
            "Sprite 500ml",
            15,
            "2026-11-20",
            1400.00,
            categoria
        );

        productoDAO.registrarProducto(producto);
    }
}