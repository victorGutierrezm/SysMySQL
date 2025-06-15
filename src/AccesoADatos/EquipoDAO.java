package AccesoADatos;

import Entidades.Equipo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipoDAO {

    public static boolean insertarEquipo(Equipo eq) {
        String sql = "INSERT INTO Equipo (Nombre, Tipo, Marca, Modelo, NumeroSerie, Estado, FechaIngreso, Ubicacion) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, eq.getNombre());
            stmt.setString(2, eq.getTipo());
            stmt.setString(3, eq.getMarca());
            stmt.setString(4, eq.getModelo());
            stmt.setString(5, eq.getNumeroSerie());
            stmt.setString(6, eq.getEstado());
            stmt.setDate(7, Date.valueOf(eq.getFechaIngreso()));
            stmt.setString(8, eq.getUbicacion());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Equipo> listarEquipos() {
        List<Equipo> lista = new ArrayList<>();
        String sql = "SELECT * FROM Equipo";

        try (Connection conn = Conexion.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Equipo eq = new Equipo(
                        rs.getInt("IdEquipo"),
                        rs.getString("Nombre"),
                        rs.getString("Tipo"),
                        rs.getString("Marca"),
                        rs.getString("Modelo"),
                        rs.getString("NumeroSerie"),
                        rs.getString("Estado"),
                        rs.getDate("FechaIngreso").toLocalDate(),
                        rs.getString("Ubicacion")
                );
                lista.add(eq);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public static boolean actualizarEquipo(Equipo eq) {
        String sql = "UPDATE Equipo SET Nombre=?, Tipo=?, Marca=?, Modelo=?, NumeroSerie=?, Estado=?, FechaIngreso=?, Ubicacion=? WHERE IdEquipo=?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, eq.getNombre());
            stmt.setString(2, eq.getTipo());
            stmt.setString(3, eq.getMarca());
            stmt.setString(4, eq.getModelo());
            stmt.setString(5, eq.getNumeroSerie());
            stmt.setString(6, eq.getEstado());
            stmt.setDate(7, Date.valueOf(eq.getFechaIngreso()));
            stmt.setString(8, eq.getUbicacion());
            stmt.setInt(9, eq.getIdEquipo());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarEquipo(int idEquipo) {
        String sql = "DELETE FROM Equipo WHERE IdEquipo = ?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEquipo);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
