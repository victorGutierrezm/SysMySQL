package AccesoADatos;
import java.sql.*;
import Entidades.Rol;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RolDAO {

    public List<Rol> obtenerRoles() {
        List<Rol> lista = new ArrayList<>();

        String sql = "SELECT IdRol, Nombre, Estado FROM Rol";

        try (Connection conn = Conexion.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("IdRol");
                String nombre = rs.getString("Nombre");
                int estado = rs.getInt("Estados");

                lista.add(new Rol(id, nombre, estado));
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener roles: " + e.getMessage());
        }

        return lista;
    }
}
