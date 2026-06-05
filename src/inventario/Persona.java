package src.inventario;

/*
 * Clase abstracta Persona.
 *
 * Representa una persona genérica del sistema.
 * No se pueden crear objetos directamente de esta clase.
 *
 * Sirve como base para otras clases, por ejemplo Usuario.
 */
public abstract class Persona {

    // Nombre de la persona
    protected String nombre;

    /*
     * Constructor de la clase Persona.
     */
    public Persona(String nombre) {
        this.nombre = nombre;
    }

    // Getter del nombre
    public String getNombre() {
        return nombre;
    }

    // Setter del nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /*
     * Método abstracto.
     *
     * Obliga a todas las clases hijas
     * a implementar este método.
     */
    public abstract void mostrarInformacion();
}