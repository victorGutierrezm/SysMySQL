package Entidades;

public class Usuario {
    private int IdUsuario;
    private String Nombre;
    private String correo;
    private String Clave;
    private int Estado;
    private int IdRol;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre, String correo, String clave, int estado, int idRol) {
        IdUsuario = idUsuario;
        Nombre = nombre;
        this.correo = correo;
        Clave = clave;
        Estado = estado;
        IdRol = idRol;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }

    public int getIdRol() {
        return IdRol;
    }

    public void setIdRol(int idRol) {
        IdRol = idRol;
    }
}
