package src.inventario;

/*
 * Clase Categoria
 * Representa una categoría dentro del inventario.
 * Ejemplos: Bebidas, Fiambres, Artículos de limpieza.
 */
public class Categoria {

    // Identificador único de la categoría
    private int idCategoria;

    // Nombre de la categoría
    private String nombreCategoria;

    /*
     * Constructor de la clase.
     * Se ejecuta cuando creamos un objeto Categoria.
     */
    public Categoria(int idCategoria, String nombreCategoria) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
    }

    // Getter del ID de categoría
    public int getIdCategoria() {
        return idCategoria;
    }

    // Setter del ID de categoría
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    // Getter del nombre de categoría
    public String getNombreCategoria() {
        return nombreCategoria;
    }

    // Setter del nombre de categoría
    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    /*
     * Sobrescribe el método toString().
     * Permite mostrar directamente el nombre de la categoría
     * cuando imprimimos un objeto.
     */
    @Override
    public String toString() {
        return nombreCategoria;
    }
}