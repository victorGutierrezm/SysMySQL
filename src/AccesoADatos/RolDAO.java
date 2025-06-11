package AccesoADatos;
import java.sql.*;
import Entidades.Rol;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RolDAO {

    public List<Rol> obtenerRoles() {
        List<Rol> lista = new ArrayList<>();

        String sql = "SELECT idrol, nombre, estado FROM rol";

        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("idrol");
                String nombre = rs.getString("nombre");
                int estado = rs.getInt("estado");

                lista.add(new Rol(id, nombre, estado));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
