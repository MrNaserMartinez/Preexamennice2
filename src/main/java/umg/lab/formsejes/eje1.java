package umg.lab.formsejes;

import umg.lab.DataBase.Model.eje1Model;
import umg.lab.DataBase.Services.eje1Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class eje1 {
    private JLabel lbl1;
    private JLabel lbl2;
    private JLabel lbl3;
    private JLabel lbl4;
    private JLabel lbl5;
    private JLabel lbl6;
    private JLabel lbl7;
    private JTextField textFieldcodigo;
    private JTextField textFieldnombre;
    private JTextField textFieldapellido;
    private JTextField textFielddepa;
    private JTextField textFieldfecha;
    private JButton bgrabar;
    private JButton bbuscar;
    private JButton actualizarButton;
    private JButton buttonborrar;
    private JPanel pepe;

    private eje1Service datosService;

    public eje1() {
        // Inicialización de los componentes
        pepe = new JPanel();
        pepe.setLayout(new GridLayout(0, 2)); // Usa GridLayout o el diseño que prefieras

        lbl1 = new JLabel("Código:");
        lbl2 = new JLabel("Nombre:");
        lbl3 = new JLabel("Apellido:");
        lbl4 = new JLabel("Departamento:");
        lbl5 = new JLabel("Fecha:");
        lbl6 = new JLabel(""); // Puedes usar para mensajes adicionales si lo deseas
        lbl7 = new JLabel(""); // Puedes usar para mensajes adicionales si lo deseas

        textFieldcodigo = new JTextField(20);
        textFieldnombre = new JTextField(20);
        textFieldapellido = new JTextField(20);
        textFielddepa = new JTextField(20);
        textFieldfecha = new JTextField(20);

        bgrabar = new JButton("Grabar");
        bbuscar = new JButton("Buscar");
        actualizarButton = new JButton("Actualizar");
        buttonborrar = new JButton("Borrar");

        // Añadir componentes al panel
        pepe.add(lbl1);
        pepe.add(textFieldcodigo);
        pepe.add(lbl2);
        pepe.add(textFieldnombre);
        pepe.add(lbl3);
        pepe.add(textFieldapellido);
        pepe.add(lbl4);
        pepe.add(textFielddepa);
        pepe.add(lbl5);
        pepe.add(textFieldfecha);
        pepe.add(bgrabar);
        pepe.add(bbuscar);
        pepe.add(actualizarButton);
        pepe.add(buttonborrar);

        // Instanciando el servicio
        this.datosService = new eje1Service();

        // Evento del botón 'Grabar' para insertar datos
        bgrabar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Parseando la fecha
                    Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(textFieldfecha.getText());

                    // Creando el modelo de datos
                    eje1Model datos = new eje1Model(
                            0,  // El código se genera automáticamente
                            textFieldnombre.getText(),
                            textFieldapellido.getText(),
                            textFielddepa.getText(),
                            fecha
                    );

                    // Insertando los datos
                    datosService.createDatos(datos);
                    JOptionPane.showMessageDialog(null, "Datos grabados con éxito.");
                    limpiarCampos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al grabar datos: " + ex.getMessage());
                }
            }
        });

        // Evento del botón 'Buscar' para buscar datos por código
        bbuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codigo = Integer.parseInt(textFieldcodigo.getText());
                    eje1Model datos = datosService.getDatosByCodigo(codigo);
                    if (datos != null) {
                        textFieldnombre.setText(datos.getNombre());
                        textFieldapellido.setText(datos.getApellido());
                        textFielddepa.setText(datos.getDepartamento());
                        textFieldfecha.setText(new SimpleDateFormat("yyyy-MM-dd").format(datos.getFechaNacimiento()));
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontraron datos para el código especificado.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al buscar datos: " + ex.getMessage());
                }
            }
        });

        // Evento del botón 'Actualizar' para actualizar los datos
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Parseando la fecha
                    Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(textFieldfecha.getText());

                    // Creando el modelo de datos con el código existente
                    eje1Model datos = new eje1Model(
                            Integer.parseInt(textFieldcodigo.getText()),  // Código existente
                            textFieldnombre.getText(),
                            textFieldapellido.getText(),
                            textFielddepa.getText(),
                            fecha
                    );

                    // Actualizando los datos
                    datosService.updateDatos(datos);
                    JOptionPane.showMessageDialog(null, "Datos actualizados con éxito.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar datos: " + ex.getMessage());
                }
            }
        });

        // Evento del botón 'Borrar' para eliminar datos
        buttonborrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codigo = Integer.parseInt(textFieldcodigo.getText());
                    int confirmacion = JOptionPane.showConfirmDialog(null,
                            "¿Está seguro de que desea borrar este registro?",
                            "Confirmar borrado", JOptionPane.YES_NO_OPTION);
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        datosService.deleteDatos(codigo);
                        JOptionPane.showMessageDialog(null, "Datos borrados con éxito.");
                        limpiarCampos();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al borrar datos: " + ex.getMessage());
                }
            }
        });
    }

    // Método para limpiar los campos del formulario
    private void limpiarCampos() {
        textFieldcodigo.setText("");
        textFieldnombre.setText("");
        textFieldapellido.setText("");
        textFielddepa.setText("");
        textFieldfecha.setText("");
    }

    public JPanel getPanel() {
        return pepe; // Devolver el panel principal
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("eje1.form");
        eje1 form = new eje1();
        frame.setContentPane(form.getPanel()); // Usar el método getPanel() para obtener el panel raíz
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}


