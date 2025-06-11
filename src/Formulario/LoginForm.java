package Formulario;

import AccesoADatos.Conexion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm {
    public JPanel panel; // debe coincidir con el nombre del JPanel en el form
    private JTextField correoTextField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginForm() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = correoTextField.getText().trim();
                String contraseña = new String(passwordField.getPassword());

                if (correo.isEmpty() || contraseña.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor completa todos los campos.");
                    return;
                }

                try (Connection conn = Conexion.getConnection()) {
                    String sql = "SELECT * FROM usuarios WHERE correo = ? AND contraseña = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, correo);
                    stmt.setString(2, contraseña);

                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "¡Bienvenido " + rs.getString("nombre") + "!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos.");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new LoginForm().panel); // panel1 no debe estar en null
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null); // Centra la ventana
        frame.setVisible(true);
    }
}
