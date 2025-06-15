package Formulario;

import AccesoADatos.HistorialMantenimientoDAO;
import Entidades.HistorialMantenimiento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class HistorialMantenimientoForm extends JFrame {
    private JTable tabla;
    private JTextField txtIdEquipo, txtFecha, txtDescripcion, txtTecnico;
    private JButton btnGuardar, btnEditar, btnEliminar;

    public HistorialMantenimientoForm() {
        setTitle("Historial de Mantenimiento");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(5, 2, 10, 5));
        txtIdEquipo = new JTextField();
        txtFecha = new JTextField();
        txtDescripcion = new JTextField();
        txtTecnico = new JTextField();

        form.add(new JLabel("ID Equipo:")); form.add(txtIdEquipo);
        form.add(new JLabel("Fecha (YYYY-MM-DD):")); form.add(txtFecha);
        form.add(new JLabel("Descripción:")); form.add(txtDescripcion);
        form.add(new JLabel("Técnico Responsable:")); form.add(txtTecnico);

        JPanel botones = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        botones.add(btnGuardar);
        botones.add(btnEditar);
        botones.add(btnEliminar);

        add(form, BorderLayout.NORTH);
        add(botones, BorderLayout.SOUTH);

        tabla = new JTable();
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnGuardar.addActionListener(e -> guardarHistorial());
        btnEditar.addActionListener(e -> editarHistorial());
        btnEliminar.addActionListener(e -> eliminarHistorial());

        tabla.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && tabla.getSelectedRow() != -1) {
                int fila = tabla.getSelectedRow();
                txtIdEquipo.setText(tabla.getValueAt(fila, 1).toString());
                txtFecha.setText(tabla.getValueAt(fila, 2).toString());
                txtDescripcion.setText(tabla.getValueAt(fila, 3).toString());
                txtTecnico.setText(tabla.getValueAt(fila, 4).toString());
            }
        });

        cargarTabla();
    }

    private void guardarHistorial() {
        try {
            int idEquipo = Integer.parseInt(txtIdEquipo.getText());
            LocalDate fecha = LocalDate.parse(txtFecha.getText());
            String descripcion = txtDescripcion.getText();
            String tecnico = txtTecnico.getText();

            HistorialMantenimiento h = new HistorialMantenimiento(0, idEquipo, fecha, descripcion, tecnico);

            if (HistorialMantenimientoDAO.insertar(h)) {
                JOptionPane.showMessageDialog(this, "Registro guardado.");
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados: " + ex.getMessage());
        }
    }

    private void editarHistorial() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un registro a editar.");
            return;
        }
        try {
            int idHistorial = (int) tabla.getValueAt(fila, 0);
            int idEquipo = Integer.parseInt(txtIdEquipo.getText());
            LocalDate fecha = LocalDate.parse(txtFecha.getText());
            String descripcion = txtDescripcion.getText();
            String tecnico = txtTecnico.getText();

            HistorialMantenimiento h = new HistorialMantenimiento(idHistorial, idEquipo, fecha, descripcion, tecnico);

            if (HistorialMantenimientoDAO.actualizar(h)) {
                JOptionPane.showMessageDialog(this, "Registro actualizado.");
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al editar: " + ex.getMessage());
        }
    }

    private void eliminarHistorial() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un registro a eliminar.");
            return;
        }
        int idHistorial = (int) tabla.getValueAt(fila, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Deseas eliminar este registro?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (HistorialMantenimientoDAO.eliminar(idHistorial)) {
                JOptionPane.showMessageDialog(this, "Registro eliminado.");
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar.");
            }
        }
    }

    private void cargarTabla() {
        String[] cols = {"ID Historial", "ID Equipo", "Fecha", "Descripción", "Técnico"};
        DefaultTableModel modelo = new DefaultTableModel(cols, 0);
        List<HistorialMantenimiento> lista = HistorialMantenimientoDAO.listar();

        for (HistorialMantenimiento h : lista) {
            modelo.addRow(new Object[]{
                    h.getIdHistorial(), h.getIdEquipo(), h.getFecha(),
                    h.getDescripcion(), h.getTecnicoResponsable()
            });
        }
        tabla.setModel(modelo);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HistorialMantenimientoForm().setVisible(true));
    }
}
