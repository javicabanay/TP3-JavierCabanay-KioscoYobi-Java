package src.inventario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * Clase ProductoDAO.
 *
 * Esta clase aplica el patrón DAO (Data Access Object).
 * Su función principal es separar la lógica de acceso a datos
 * de la lógica principal del sistema.
 *
 * Desde esta clase se realizan las operaciones de persistencia
 * sobre la tabla producto de la base de datos MySQL.
 *
 * Las operaciones implementadas son:
 * - Listar productos.
 * - Registrar productos.
 * - Actualizar stock.
 * - Actualizar precio.
 * - Eliminar productos.
 * - Consultar productos próximos a vencer.
 */
public class ProductoDAO {

    /*
     * Método listarProductos().
     *
     * Consulta todos los productos registrados en la base de datos MySQL.
     * Los datos obtenidos se almacenan en un ArrayList de objetos Producto.
     *
     * Además, realiza un INNER JOIN con la tabla categoria para obtener
     * el nombre de la categoría asociada a cada producto.
     */
    public ArrayList<Producto> listarProductos() {

        ArrayList<Producto> productos = new ArrayList<>();

        String sql = "SELECT p.id_producto, p.nombre, p.cantidad_stock, " +
                     "p.fecha_vencimiento, p.precio, " +
                     "c.id_categoria, c.nombre_categoria " +
                     "FROM producto p " +
                     "INNER JOIN categoria c ON p.id_categoria = c.id_categoria " +
                     "ORDER BY p.id_producto ASC";

        try {
            Connection conexion = ConexionBD.conectar();
            PreparedStatement consulta = conexion.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();

            while (resultado.next()) {

                Categoria categoria = new Categoria(
                    resultado.getInt("id_categoria"),
                    resultado.getString("nombre_categoria")
                );

                Producto producto = new Producto(
                    resultado.getInt("id_producto"),
                    resultado.getString("nombre"),
                    resultado.getInt("cantidad_stock"),
                    resultado.getString("fecha_vencimiento"),
                    resultado.getDouble("precio"),
                    categoria
                );

                productos.add(producto);
            }

            resultado.close();
            consulta.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }

        return productos;
    }

    /*
     * Método registrarProducto().
     *
     * Inserta un nuevo producto en la tabla producto.
     * Para evitar errores y mejorar la seguridad de la consulta,
     * se utiliza PreparedStatement con parámetros.
     */
    public void registrarProducto(Producto producto) {

        String sql = "INSERT INTO producto " +
                     "(nombre, cantidad_stock, fecha_vencimiento, precio, id_categoria) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conexion = ConexionBD.conectar();
            PreparedStatement consulta = conexion.prepareStatement(sql);

            consulta.setString(1, producto.getNombre());
            consulta.setInt(2, producto.getCantidadStock());
            consulta.setString(3, producto.getFechaVencimiento());
            consulta.setDouble(4, producto.getPrecio());
            consulta.setInt(5, producto.getCategoria().getIdCategoria());

            int filasAfectadas = consulta.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Producto registrado correctamente en MySQL.");
            }

            consulta.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error al registrar producto: " + e.getMessage());
        }
    }

    /*
     * Método actualizarStock().
     *
     * Actualiza la cantidad de stock de un producto existente.
     * La búsqueda del producto se realiza mediante su id_producto.
     */
    public void actualizarStock(int idProducto, int nuevoStock) {

        String sql = "UPDATE producto SET cantidad_stock = ? WHERE id_producto = ?";

        try {
            Connection conexion = ConexionBD.conectar();
            PreparedStatement consulta = conexion.prepareStatement(sql);

            consulta.setInt(1, nuevoStock);
            consulta.setInt(2, idProducto);

            int filasAfectadas = consulta.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Stock actualizado correctamente en MySQL.");
            } else {
                System.out.println("No se encontró un producto con ese ID.");
            }

            consulta.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error al actualizar stock: " + e.getMessage());
        }
    }

    /*
     * Método actualizarPrecio().
     *
     * Actualiza el precio de un producto existente.
     * La búsqueda del producto se realiza mediante su id_producto.
     */
    public void actualizarPrecio(int idProducto, double nuevoPrecio) {

        String sql = "UPDATE producto SET precio = ? WHERE id_producto = ?";

        try {
            Connection conexion = ConexionBD.conectar();
            PreparedStatement consulta = conexion.prepareStatement(sql);

            consulta.setDouble(1, nuevoPrecio);
            consulta.setInt(2, idProducto);

            int filasAfectadas = consulta.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Precio actualizado correctamente en MySQL.");
            } else {
                System.out.println("No se encontró un producto con ese ID.");
            }

            consulta.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error al actualizar precio: " + e.getMessage());
        }
    }

    /*
     * Método eliminarProducto().
     *
     * Elimina un producto registrado en la tabla producto.
     * La eliminación se realiza mediante el id_producto.
     */
    public void eliminarProducto(int idProducto) {

        String sql = "DELETE FROM producto WHERE id_producto = ?";

        try {
            Connection conexion = ConexionBD.conectar();
            PreparedStatement consulta = conexion.prepareStatement(sql);

            consulta.setInt(1, idProducto);

            int filasAfectadas = consulta.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Producto eliminado correctamente de MySQL.");
            } else {
                System.out.println("No se encontró un producto con ese ID.");
            }

            consulta.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
    }

    /*
     * Método listarProductosProximosAVencer().
     *
     * Consulta los productos cuya fecha de vencimiento sea menor
     * o igual a la fecha límite ingresada por el usuario.
     *
     * Los productos encontrados se ordenan por fecha de vencimiento
     * de manera ascendente, para mostrar primero los más cercanos a vencer.
     */
    public ArrayList<Producto> listarProductosProximosAVencer(String fechaLimite) {

        ArrayList<Producto> productos = new ArrayList<>();

        String sql = "SELECT p.id_producto, p.nombre, p.cantidad_stock, " +
                     "p.fecha_vencimiento, p.precio, " +
                     "c.id_categoria, c.nombre_categoria " +
                     "FROM producto p " +
                     "INNER JOIN categoria c ON p.id_categoria = c.id_categoria " +
                     "WHERE p.fecha_vencimiento <= ? " +
                     "ORDER BY p.fecha_vencimiento ASC";

        try {
            Connection conexion = ConexionBD.conectar();
            PreparedStatement consulta = conexion.prepareStatement(sql);

            consulta.setString(1, fechaLimite);

            ResultSet resultado = consulta.executeQuery();

            while (resultado.next()) {

                Categoria categoria = new Categoria(
                    resultado.getInt("id_categoria"),
                    resultado.getString("nombre_categoria")
                );

                Producto producto = new Producto(
                    resultado.getInt("id_producto"),
                    resultado.getString("nombre"),
                    resultado.getInt("cantidad_stock"),
                    resultado.getString("fecha_vencimiento"),
                    resultado.getDouble("precio"),
                    categoria
                );

                productos.add(producto);
            }

            resultado.close();
            consulta.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error al consultar productos próximos a vencer: " + e.getMessage());
        }

        return productos;
    }
}