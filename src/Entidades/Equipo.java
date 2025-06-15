package Entidades;

import java.time.LocalDate;

public class Equipo {
    private int idEquipo;
    private String nombre;
    private String tipo;
    private String marca;
    private String modelo;
    private String numeroSerie;
    private String estado;
    private LocalDate fechaIngreso;
    private String ubicacion;

    public Equipo() {}

    public Equipo(int idEquipo, String nombre, String tipo, String marca, String modelo,
                  String numeroSerie, String estado, LocalDate fechaIngreso, String ubicacion) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.estado = estado;
        this.fechaIngreso = fechaIngreso;
        this.ubicacion = ubicacion;
    }

    // Getters
    public int getIdEquipo() { return idEquipo; }
    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getNumeroSerie() { return numeroSerie; }
    public String getEstado() { return estado; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public String getUbicacion() { return ubicacion; }

    // Setters
    public void setIdEquipo(int idEquipo) { this.idEquipo = idEquipo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setNumeroSerie(String numeroSerie) { this.numeroSerie = numeroSerie; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
}
