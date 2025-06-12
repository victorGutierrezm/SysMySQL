package AccesoADatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URl = "jdbc:sqlserver://BD_System.mssql.somee.com:1433;databaseName=BD_System;encrypt=true;trustServerCertificate=true";
    private static final String USER = "dev_sistema_SQLLogin_1";
    private static final String PASSWORD = "ytps89h3ac";
//hols mundo
    public static Connection getConexion() {
        try {
            // Incluye usuario y contraseña directamente en la URL
            return DriverManager.getConnection(URl + ";user=" + USER + ";password=" + PASSWORD);
        } catch (SQLException e) {
            System.out.println("❌ Error de conexión}: " + e.getMessage());
            return null;
        }
    }
}