package Presentacion;

import AccesoADatos.RolDAO;
import Entidades.Rol;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RolTablaForm extends JFrame {
    private JTable tabla;
    private DefaultTableModel modelo;

    public RolTablaForm() {
        setTitle("Vista de Roles");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear modelo de tabla y tabla
        modelo = new DefaultTableModel();
        tabla = new JTable(modelo);

        // Agregar columnas
        modelo.addColumn("ID Rol");
        modelo.addColumn("Nombre");
        modelo.addColumn("Estado");

        // Llenar datos desde DAO
        cargarDatos();

        // Agregar JScrollPane
        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void cargarDatos() {
        RolDAO dao = new RolDAO();
        List<Rol> lista = dao.obtenerRoles();

        for (Rol r : lista) {
            // Si quieres mostrar "Activo"/"Inactivo" en lugar de 1/0:
            String estadoTexto = (r.getEstado() == 1) ? "Activo" : "Inactivo";
            modelo.addRow(new Object[]{r.getId(), r.getNombre(), estadoTexto});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RolTablaForm().setVisible(true);
        });
    }
}
