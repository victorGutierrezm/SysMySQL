package Formulario;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipalForm extends JFrame {

    public MenuPrincipalForm() {
        setTitle("Menú Principal");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 20, 20));

        JLabel titulo = new JLabel("Bienvenido al Menú Principal", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JButton btnEquipos = new JButton("Gestión de Equipos");
        JButton btnHistorial = new JButton("Historial de Mantenimiento");

        // Estilo
        btnEquipos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnHistorial.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        // Acciones
        btnEquipos.addActionListener(e -> {
            EquipoForm ventanaEquipos = new EquipoForm();
            ventanaEquipos.setVisible(true);
        });

        btnHistorial.addActionListener(e -> {
            HistorialMantenimientoForm ventanaHistorial = new HistorialMantenimientoForm();
            ventanaHistorial.setVisible(true);
        });

        panel.add(titulo);
        panel.add(btnEquipos);
        panel.add(btnHistorial);

        add(panel);
    }

    // Método para abrir el menú desde LoginForm
    public static void mostrar() {
        SwingUtilities.invokeLater(() -> {
            MenuPrincipalForm menu = new MenuPrincipalForm();
            menu.setVisible(true);
        });
    }
}
