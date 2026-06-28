package src.inventario;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * Clase MainBD.
 *
 * Esta clase representa el punto de entrada del prototipo
 * conectado a la base de datos MySQL.
 *
 * Su función principal es mostrar un menú interactivo por consola
 * que permite al usuario realizar operaciones sobre el inventario.
 *
 * Las operaciones disponibles son:
 * - Listar productos.
 * - Registrar productos.
 * - Actualizar stock.
 * - Actualizar precio.
 * - Eliminar productos.
 * - Detectar productos próximos a vencer.
 *
 * Para acceder a la base de datos, esta clase utiliza ProductoDAO,
 * aplicando así una separación entre la interacción con el usuario
 * y la lógica de persistencia de datos.
 */
public class MainBD {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ProductoDAO productoDAO = new ProductoDAO();

        /*
         * Arreglo utilizado para mostrar las categorías disponibles.
         * Este arreglo permite cumplir con la utilización complementaria
         * de arreglos solicitada en la consigna del trabajo.
         */
        String[] categoriasDisponibles = {
            "Mercadería general",
            "Bebidas",
            "Fiambres",
            "Artículos de limpieza"
        };

        int opcion = 0;

        /*
         * Estructura repetitiva do-while.
         * Permite mantener activo el menú hasta que el usuario
         * seleccione la opción de salida.
         */
        do {
            try {
                System.out.println("\n=================================");
                System.out.println(" SISTEMA DE INVENTARIO - KIOSCO YOBI ");
                System.out.println("        CON BASE DE DATOS MYSQL       ");
                System.out.println("=================================");
                System.out.println("1. Listar productos");
                System.out.println("2. Registrar producto");
                System.out.println("3. Actualizar stock");
                System.out.println("4. Actualizar precio");
                System.out.println("5. Eliminar producto");
                System.out.println("6. Detectar productos próximos a vencer");
                System.out.println("7. Salir");
                System.out.print("Seleccione una opción: ");

                opcion = Integer.parseInt(scanner.nextLine());

                /*
                 * Estructura switch.
                 * Permite ejecutar una acción determinada según
                 * la opción ingresada por el usuario.
                 */
                switch (opcion) {

                    case 1:
                        listarProductos(productoDAO);
                        break;

                    case 2:
                        registrarProducto(scanner, productoDAO, categoriasDisponibles);
                        break;

                    case 3:
                        actualizarStock(scanner, productoDAO);
                        break;

                    case 4:
                        actualizarPrecio(scanner, productoDAO);
                        break;

                    case 5:
                        eliminarProducto(scanner, productoDAO);
                        break;

                    case 6:
                        detectarProximosAVencer(scanner, productoDAO);
                        break;

                    case 7:
                        System.out.println("Saliendo del sistema...");
                        break;

                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número válido.");
            }

        } while (opcion != 7);

        scanner.close();
    }

    /*
     * Método listarProductos().
     *
     * Solicita al ProductoDAO el listado de productos registrados
     * en la base de datos MySQL y los muestra por consola.
     *
     * Los productos son recibidos en un ArrayList.
     */
    public static void listarProductos(ProductoDAO productoDAO) {

        ArrayList<Producto> productos = productoDAO.listarProductos();

        System.out.println("\nListado de productos desde MySQL:");
        System.out.println("---------------------------------");

        if (productos.isEmpty()) {
            System.out.println("No existen productos registrados.");
        } else {
            for (Producto producto : productos) {
                System.out.println(producto);
            }
        }
    }

    /*
     * Método registrarProducto().
     *
     * Solicita al usuario los datos necesarios para registrar
     * un nuevo producto en la base de datos.
     *
     * Luego crea un objeto Producto y lo envía a ProductoDAO
     * para que sea guardado en MySQL.
     */
    public static void registrarProducto(Scanner scanner, ProductoDAO productoDAO, String[] categoriasDisponibles) {

        try {
            System.out.println("\nRegistro de producto");
            System.out.println("--------------------");

            System.out.print("Ingrese nombre del producto: ");
            String nombre = scanner.nextLine();

            System.out.print("Ingrese cantidad en stock: ");
            int stock = Integer.parseInt(scanner.nextLine());

            System.out.print("Ingrese fecha de vencimiento (AAAA-MM-DD): ");
            String fechaVencimiento = scanner.nextLine();

            System.out.print("Ingrese precio: ");
            double precio = Double.parseDouble(scanner.nextLine());

            System.out.println("\nCategorías disponibles:");

            for (int i = 0; i < categoriasDisponibles.length; i++) {
                System.out.println((i + 1) + ". " + categoriasDisponibles[i]);
            }

            System.out.print("Seleccione una categoría: ");
            int opcionCategoria = Integer.parseInt(scanner.nextLine());

            if (opcionCategoria < 1 || opcionCategoria > categoriasDisponibles.length) {
                System.out.println("Categoría inválida.");
                return;
            }

            int idCategoria = opcionCategoria;
            String nombreCategoria = categoriasDisponibles[opcionCategoria - 1];

            Categoria categoria = new Categoria(idCategoria, nombreCategoria);

            Producto producto = new Producto(
                0,
                nombre,
                stock,
                fechaVencimiento,
                precio,
                categoria
            );

            productoDAO.registrarProducto(producto);

        } catch (NumberFormatException e) {
            System.out.println("Error: debe ingresar valores numéricos válidos.");
        }
    }

    /*
     * Método actualizarStock().
     *
     * Permite modificar la cantidad disponible de un producto
     * registrado en la base de datos.
     */
    public static void actualizarStock(Scanner scanner, ProductoDAO productoDAO) {

        try {
            System.out.println("\nActualizar stock");
            System.out.println("----------------");

            System.out.print("Ingrese el ID del producto: ");
            int idProducto = Integer.parseInt(scanner.nextLine());

            System.out.print("Ingrese el nuevo stock: ");
            int nuevoStock = Integer.parseInt(scanner.nextLine());

            productoDAO.actualizarStock(idProducto, nuevoStock);

        } catch (NumberFormatException e) {
            System.out.println("Error: debe ingresar valores numéricos válidos.");
        }
    }

    /*
     * Método actualizarPrecio().
     *
     * Permite modificar el precio de un producto registrado
     * en la base de datos.
     */
    public static void actualizarPrecio(Scanner scanner, ProductoDAO productoDAO) {

        try {
            System.out.println("\nActualizar precio");
            System.out.println("-----------------");

            System.out.print("Ingrese el ID del producto: ");
            int idProducto = Integer.parseInt(scanner.nextLine());

            System.out.print("Ingrese el nuevo precio: ");
            double nuevoPrecio = Double.parseDouble(scanner.nextLine());

            productoDAO.actualizarPrecio(idProducto, nuevoPrecio);

        } catch (NumberFormatException e) {
            System.out.println("Error: debe ingresar valores numéricos válidos.");
        }
    }

    /*
     * Método eliminarProducto().
     *
     * Permite eliminar un producto de la base de datos
     * utilizando su ID.
     */
    public static void eliminarProducto(Scanner scanner, ProductoDAO productoDAO) {

        try {
            System.out.println("\nEliminar producto");
            System.out.println("-----------------");

            System.out.print("Ingrese el ID del producto a eliminar: ");
            int idProducto = Integer.parseInt(scanner.nextLine());

            productoDAO.eliminarProducto(idProducto);

        } catch (NumberFormatException e) {
            System.out.println("Error: debe ingresar un ID válido.");
        }
    }

    /*
     * Método detectarProximosAVencer().
     *
     * Permite consultar productos cuya fecha de vencimiento
     * sea menor o igual a la fecha límite ingresada por el usuario.
     */
    public static void detectarProximosAVencer(Scanner scanner, ProductoDAO productoDAO) {

        System.out.println("\nProductos próximos a vencer");
        System.out.println("---------------------------");

        System.out.print("Ingrese fecha límite (AAAA-MM-DD): ");
        String fechaLimite = scanner.nextLine();

        ArrayList<Producto> productos = productoDAO.listarProductosProximosAVencer(fechaLimite);

        System.out.println("\nProductos próximos a vencer hasta la fecha: " + fechaLimite);
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

