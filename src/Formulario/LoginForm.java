package Formulario;

import AccesoADatos.Conexion;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import Formulario.LogicaDeNegocio.Estilos;
import Formulario.MenuPrincipalForm;

public class LoginForm {
    public JPanel panel;
    private JTextField correoTextField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginForm() {
        // APLICAR ESTILO A COMPONENTES
        Estilos.estilizarCampoTexto(correoTextField);
        Estilos.estilizarCampoTexto(passwordField);
        Estilos.estilizarBoton(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = correoTextField.getText().trim();
                String contrasena = new String(passwordField.getPassword());

                if (correo.isEmpty() || contrasena.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor completa todos los campos.");
                    return;
                }

                try (Connection conn = Conexion.getConexion()) {
                    String sql = "SELECT Nombre FROM Usuario WHERE Correo = ? AND Clave = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, correo);
                    stmt.setString(2, contrasena);

                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "¡Bienvenido/a " + rs.getString("Nombre") + "!");

                        SwingUtilities.invokeLater(() -> {
                            JFrame loginFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
                            loginFrame.dispose();
                            MenuPrincipalForm.mostrar(); // asegúrate que existe
                        });

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
        LoginForm loginForm = new LoginForm();
        frame.setContentPane(loginForm.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ESTILO VISUAL A LA VENTANA
        Estilos.aplicarEstiloFormulario(frame);

        frame.setVisible(true);
    }
}
