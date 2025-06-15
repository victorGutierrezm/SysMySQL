package AccesoADatos;

import Entidades.HistorialMantenimiento;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HistorialMantenimientoDAO {

    public static boolean insertar(HistorialMantenimiento h) {
        String sql = "INSERT INTO HistorialMantenimiento (IdEquipo, Fecha, Descripcion, TecnicoResponsable) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexion.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, h.getIdEquipo());
            stmt.setDate(2, Date.valueOf(h.getFecha()));
            stmt.setString(3, h.getDescripcion());
            stmt.setString(4, h.getTecnicoResponsable());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<HistorialMantenimiento> listar() {
        List<HistorialMantenimiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM HistorialMantenimiento";
        try (Connection conn = Conexion.getConexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                HistorialMantenimiento h = new HistorialMantenimiento(
                        rs.getInt("IdHistorial"),
                        rs.getInt("IdEquipo"),
                        rs.getDate("Fecha").toLocalDate(),
                        rs.getString("Descripcion"),
                        rs.getString("TecnicoResponsable")
                );
                lista.add(h);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static boolean actualizar(HistorialMantenimiento h) {
        String sql = "UPDATE HistorialMantenimiento SET IdEquipo=?, Fecha=?, Descripcion=?, TecnicoResponsable=? WHERE IdHistorial=?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, h.getIdEquipo());
            stmt.setDate(2, Date.valueOf(h.getFecha()));
            stmt.setString(3, h.getDescripcion());
            stmt.setString(4, h.getTecnicoResponsable());
            stmt.setInt(5, h.getIdHistorial());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminar(int idHistorial) {
        String sql = "DELETE FROM HistorialMantenimiento WHERE IdHistorial=?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idHistorial);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
