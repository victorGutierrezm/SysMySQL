package AccesoADatos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // Cambia "nombre_de_tu_base" por el nombre real de tu base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/BDLogin?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // o el usuario que uses
    private static final String PASS = "Admin17032005##"; // pon tu contraseña de MySQL


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
