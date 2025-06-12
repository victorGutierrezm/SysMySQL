package Formulario;

import AccesoADatos.RolDAO;
import Entidades.Rol;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class MenuPrincipalForm {
    public JPanel panel;
    private JTable tablaRoles;

    public MenuPrincipalForm() {
        cargarTablaRoles();
    }

    private void cargarTablaRoles() {
        RolDAO dao = new RolDAO();
        List<Rol> lista = dao.obtenerRoles();

        String[] columnas = {"ID Rol", "Nombre", "Estados"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (Rol rol : lista) {
            String estadoTexto = (rol.getEstado() == 1) ? "Activo" : "Inactivo";
            modelo.addRow(new Object[]{
                    rol.getIdRol(),
                    rol.getNombre(),
                    estadoTexto
            });
        }

        tablaRoles.setModel(modelo);
    }

    public static void mostrar() {
        JFrame frame = new JFrame("Menú Principal");
        frame.setContentPane(new MenuPrincipalForm().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}