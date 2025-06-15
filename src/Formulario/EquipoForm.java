package Formulario;

import AccesoADatos.EquipoDAO;
import Entidades.Equipo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class EquipoForm extends JFrame {
    private JTable tabla;
    private JTextField txtNombre, txtTipo, txtMarca, txtModelo, txtSerie, txtEstado, txtFecha, txtUbicacion;
    private JButton btnGuardar, btnEditar, btnEliminar;

    public EquipoForm() {
        setTitle("Gestión de Equipos");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(9, 2, 10, 5));
        txtNombre = new JTextField(); txtTipo = new JTextField();
        txtMarca = new JTextField(); txtModelo = new JTextField();
        txtSerie = new JTextField(); txtEstado = new JTextField();
        txtFecha = new JTextField(); txtUbicacion = new JTextField();

        form.add(new JLabel("Nombre:")); form.add(txtNombre);
        form.add(new JLabel("Tipo:")); form.add(txtTipo);
        form.add(new JLabel("Marca:")); form.add(txtMarca);
        form.add(new JLabel("Modelo:")); form.add(txtModelo);
        form.add(new JLabel("N° Serie:")); form.add(txtSerie);
        form.add(new JLabel("Estado:")); form.add(txtEstado);
        form.add(new JLabel("Fecha Ingreso (YYYY-MM-DD):")); form.add(txtFecha);
        form.add(new JLabel("Ubicación:")); form.add(txtUbicacion);

        btnGuardar = new JButton("Guardar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");

        JPanel botones = new JPanel();
        botones.add(btnGuardar);
        botones.add(btnEditar);
        botones.add(btnEliminar);

        add(form, BorderLayout.NORTH);

        tabla = new JTable();
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> guardarEquipo());
        btnEditar.addActionListener(e -> editarEquipo());
        btnEliminar.addActionListener(e -> eliminarEquipo());

        tabla.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && tabla.getSelectedRow() != -1) {
                int fila = tabla.getSelectedRow();
                txtNombre.setText(tabla.getValueAt(fila, 1).toString());
                txtTipo.setText(tabla.getValueAt(fila, 2).toString());
                txtMarca.setText(tabla.getValueAt(fila, 3).toString());
                txtModelo.setText(tabla.getValueAt(fila, 4).toString());
                txtSerie.setText(tabla.getValueAt(fila, 5).toString());
                txtEstado.setText(tabla.getValueAt(fila, 6).toString());
                txtFecha.setText(tabla.getValueAt(fila, 7).toString());
                txtUbicacion.setText(tabla.getValueAt(fila, 8).toString());
            }
        });

        cargarTabla();
    }

    private void guardarEquipo() {
        try {
            Equipo eq = new Equipo(0, txtNombre.getText(), txtTipo.getText(), txtMarca.getText(), txtModelo.getText(),
                    txtSerie.getText(), txtEstado.getText(), LocalDate.parse(txtFecha.getText()), txtUbicacion.getText());

            if (EquipoDAO.insertarEquipo(eq)) {
                JOptionPane.showMessageDialog(this, "Equipo guardado.");
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en la fecha o campos inválidos: " + ex.getMessage());
        }
    }

    private void editarEquipo() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un equipo a editar.");
            return;
        }
        try {
            int id = (int) tabla.getValueAt(fila, 0);
            Equipo eq = new Equipo(id, txtNombre.getText(), txtTipo.getText(), txtMarca.getText(), txtModelo.getText(),
                    txtSerie.getText(), txtEstado.getText(), LocalDate.parse(txtFecha.getText()), txtUbicacion.getText());

            if (EquipoDAO.actualizarEquipo(eq)) {
                JOptionPane.showMessageDialog(this, "Equipo actualizado.");
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en la fecha o campos inválidos: " + ex.getMessage());
        }
    }

    private void eliminarEquipo() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un equipo a eliminar.");
            return;
        }
        int id = (int) tabla.getValueAt(fila, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar este equipo?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (EquipoDAO.eliminarEquipo(id)) {
                JOptionPane.showMessageDialog(this, "Equipo eliminado.");
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar.");
            }
        }
    }

    private void cargarTabla() {
        String[] cols = {"ID", "Nombre", "Tipo", "Marca", "Modelo", "Serie", "Estado", "Fecha", "Ubicación"};
        DefaultTableModel modelo = new DefaultTableModel(cols, 0);
        List<Equipo> lista = EquipoDAO.listarEquipos();

        for (Equipo e : lista) {
            modelo.addRow(new Object[]{
                    e.getIdEquipo(), e.getNombre(), e.getTipo(), e.getMarca(),
                    e.getModelo(), e.getNumeroSerie(), e.getEstado(),
                    e.getFechaIngreso(), e.getUbicacion()
            });
        }
        tabla.setModel(modelo);
    }
}
