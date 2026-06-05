package src.inventario;

/*
 * La clase Usuario hereda de Persona.
 * Gracias a esto reutilizamos el atributo nombre
 * y el método abstracto mostrarInformacion().
 */
public class Usuario extends Persona {

    // Atributos propios del usuario
    private String rol;
    private String nombreUsuario;

    /*
     * Constructor.
     * super(nombre) llama al constructor de Persona.
     */
    public Usuario(String nombre, String rol, String nombreUsuario) {
        super(nombre);
        this.rol = rol;
        this.nombreUsuario = nombreUsuario;
    }

    // Getter del rol
    public String getRol() {
        return rol;
    }

    // Setter del rol
    public void setRol(String rol) {
        this.rol = rol;
    }

    // Getter del nombre de usuario
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    // Setter del nombre de usuario
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /*
     * Polimorfismo:
     * estamos sobrescribiendo el método abstracto
     * definido en la clase Persona.
     */
    @Override
    public void mostrarInformacion() {
        System.out.println("Usuario: " + nombre);
        System.out.println("Rol: " + rol);
        System.out.println("Login: " + nombreUsuario);
    }
}
