package Entidades;

import java.time.LocalDate;

public class HistorialMantenimiento {
    private int idHistorial;
    private int idEquipo;
    private LocalDate fecha;
    private String descripcion;
    private String tecnicoResponsable;

    public HistorialMantenimiento(int idHistorial, int idEquipo, LocalDate fecha, String descripcion, String tecnicoResponsable) {
        this.idHistorial = idHistorial;
        this.idEquipo = idEquipo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.tecnicoResponsable = tecnicoResponsable;
    }

    public int getIdHistorial() { return idHistorial; }
    public int getIdEquipo() { return idEquipo; }
    public LocalDate getFecha() { return fecha; }
    public String getDescripcion() { return descripcion; }
    public String getTecnicoResponsable() { return tecnicoResponsable; }

    public void setIdHistorial(int idHistorial) { this.idHistorial = idHistorial; }
    public void setIdEquipo(int idEquipo) { this.idEquipo = idEquipo; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setTecnicoResponsable(String tecnicoResponsable) { this.tecnicoResponsable = tecnicoResponsable; }
}
