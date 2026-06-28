package src.inventario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Clase ConexionBD.
 *
 * Esta clase se encarga de establecer la conexión entre
 * la aplicación Java y la base de datos MySQL del sistema.
 *
 * La conexión se realiza mediante JDBC, utilizando la URL
 * de la base de datos, el usuario y la contraseña definidos.
 *
 * Esta clase permite centralizar la conexión para que otras
 * clases, como ProductoDAO, puedan reutilizarla sin repetir código.
 */
public class ConexionBD {

    // URL de conexión a la base de datos kiosco_yobi.
    private static final String URL = "jdbc:mysql://localhost:3306/kiosco_yobi";

    // Usuario configurado en MySQL Workbench.
    private static final String USUARIO = "root";

    // Contraseña del usuario root de MySQL.
    private static final String PASSWORD = "Javito135."; // Sacá el punto si tu contraseña no lo tiene.

    /*
     * Método conectar().
     *
     * Intenta establecer una conexión con la base de datos MySQL.
     * Si la conexión se realiza correctamente, devuelve un objeto Connection.
     * Si ocurre un error, captura la excepción y muestra un mensaje.
     */
    public static Connection conectar() {
        try {
            Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("Conexión exitosa a MySQL.");
            return conexion;

        } catch (SQLException e) {
            System.out.println("Error al conectar con MySQL: " + e.getMessage());
            return null;
        }
    }
}