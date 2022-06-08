
package Proyecto;

public abstract class Usuario {
    
    protected String nombre;
    protected String apellido;
    protected String usuario;
    protected String contrasenia;
    protected char tipo;

    public Usuario(String nombre, String apellido, String usuario, String contrasenia, char tipo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.tipo = tipo;
    }
    
    
}
