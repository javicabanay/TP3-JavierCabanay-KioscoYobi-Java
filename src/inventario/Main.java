package src.inventario;

import java.util.Scanner;

/*
 * Clase Main.
 *
 * Es el punto de entrada del programa.
 * Desde acá se ejecuta el menú principal del sistema.
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Inventario inventario = new Inventario();

        Categoria bebidas = new Categoria(1, "Bebidas");
        Categoria fiambres = new Categoria(2, "Fiambres");
        Categoria limpieza = new Categoria(3, "Artículos de limpieza");
        Categoria mercaderia = new Categoria(4, "Mercadería general");

        int opcion = 0;
        int contadorProductos = 1;

        do {
            System.out.println("\n=========================================");
            System.out.println("   SISTEMA DE INVENTARIO - KIOSCO YOBI");
            System.out.println("=========================================");
            
            System.out.println("1. Registrar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Buscar producto");
            System.out.println("4. Ordenar productos por nombre");
            System.out.println("5. Actualizar stock");
            System.out.println("6. Actualizar precio");
            System.out.println("7. Detectar productos próximos a vencer");
            System.out.println("8. Eliminar producto");
            System.out.println("9. Mostrar valor total del inventario");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {

                    case 1:
                        System.out.print("Ingrese nombre del producto: ");
                        String nombre = scanner.nextLine();

                        System.out.print("Ingrese cantidad en stock: ");
                        int stock = Integer.parseInt(scanner.nextLine());

                        System.out.print("Ingrese precio del producto: ");
                        double precio = Double.parseDouble(scanner.nextLine());

                        System.out.print("Ingrese fecha de vencimiento (AAAA-MM-DD): ");
                        String fecha = scanner.nextLine();

                        System.out.println("Seleccione categoría:");
                        System.out.println("1. Bebidas");
                        System.out.println("2. Fiambres");
                        System.out.println("3. Artículos de limpieza");
                        System.out.println("4. Mercadería general");
                        System.out.print("Opción: ");

                        int opcionCategoria = Integer.parseInt(scanner.nextLine());
                        Categoria categoriaSeleccionada;

                        if (opcionCategoria == 1) {
                            categoriaSeleccionada = bebidas;
                        } else if (opcionCategoria == 2) {
                            categoriaSeleccionada = fiambres;
                        } else if (opcionCategoria == 3) {
                            categoriaSeleccionada = limpieza;
                        } else {
                            categoriaSeleccionada = mercaderia;
                        }

                        Producto producto = new Producto(
                                       contadorProductos,
                                       nombre,
                                       stock,
                                       fecha,
                                       precio,
                                       categoriaSeleccionada
                        );

                        inventario.agregarProducto(producto);
                        contadorProductos++;
                        break;

                    case 2:
                        inventario.listarProductos();
                        break;

                    case 3:
                        System.out.print("Ingrese el nombre del producto a buscar: ");
                        String nombreBuscar = scanner.nextLine();

                        Producto encontrado = inventario.buscarProducto(nombreBuscar);

                        if (encontrado != null) {
                            System.out.println("Producto encontrado:");
                            System.out.println(encontrado);
                        } else {
                            System.out.println("Producto no encontrado.");
                        }
                        break;

                    case 4:
                        inventario.ordenarPorNombre();
                        break;

                    case 5:
                        System.out.print("Ingrese el nombre del producto: ");
                        String nombreStock = scanner.nextLine();

                        System.out.print("Ingrese el nuevo stock: ");
                        int nuevoStock = Integer.parseInt(scanner.nextLine());

                        inventario.actualizarStock(nombreStock, nuevoStock);
                        break;
                    
                    case 6:
                         System.out.print("Ingrese el nombre del producto: ");
                         String nombrePrecio = scanner.nextLine();

                         System.out.print("Ingrese el nuevo precio: ");
                         double nuevoPrecio = Double.parseDouble(scanner.nextLine());

                         inventario.actualizarPrecio(nombrePrecio, nuevoPrecio);
                         break;    

                    case 7:
                        System.out.print("Ingrese fecha límite de vencimiento (AAAA-MM-DD): ");
                        String fechaLimite = scanner.nextLine();

                        inventario.detectarProximosAVencer(fechaLimite);
                        break;

                    case 8:

                         System.out.print("Ingrese el nombre del producto a eliminar: ");
                         String nombreEliminar = scanner.nextLine();

                         inventario.eliminarProducto(nombreEliminar);

                         break;
                         
                    case 9:

                         inventario.mostrarValorTotalInventario();

                         break;     

                    case 10:
                        System.out.println("Saliendo del sistema...");
                        break;

                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número válido.");
            } catch (Exception e) {
                System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            }

        } while (opcion != 10);

        scanner.close();
    }
}