package Entidades;

public class Rol {
    private int IdRol;
    private String Nombre;
    private int Estado;

    public Rol() {
    }

    public Rol(int idRol, String nombre, int estado) {
        this.IdRol = idRol;
        this.Nombre = nombre;
        this.Estado = estado;
    }

    // Getter compatible con el JTable
    public int getId() {
        return IdRol;
    }

    // También puedes seguir usando este si lo prefieres
    public int getIdRol() {
        return IdRol;
    }

    public void setIdRol(int idRol) {
        this.IdRol = idRol;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        this.Estado = estado;
    }
}
