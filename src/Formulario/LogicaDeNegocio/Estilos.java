package Formulario.LogicaDeNegocio;

import javax.swing.*;
import java.awt.*;

public class Estilos {

    public static void aplicarEstiloFormulario(JFrame frame) {
        frame.getContentPane().setBackground(new Color(245, 245, 250)); // color claro
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); // Centrar ventana
    }

    public static void estilizarBoton(JButton boton) {
        boton.setFocusPainted(false);
        boton.setBackground(new Color(51, 153, 255)); // azul
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));
    }

    public static void estilizarCampoTexto(JTextField campo) {
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        campo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }

    public static void estilizarEtiqueta(JLabel label) {
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(50, 50, 50));
    }
}
