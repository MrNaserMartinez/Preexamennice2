package umg.lab.formsejes;

import umg.lab.DataBase.Model.eje2Model;
import umg.lab.DataBase.Services.eje2Service;
import umg.lab.DataBaseConeccion.DatabaseConnection; // Importar la clase de conexión

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class eje2 {
    private JLabel lbl1;
    private JLabel lbl2;
    private JLabel lbl3;
    private JLabel lbl4;
    private JLabel lbl5;
    private JLabel lbl6;
    private JLabel lbl7;
    private JTextField textFieldcarne;
    private JTextField textFieldnombre;
    private JTextField textFieldcorreo;
    private JComboBox<String> comboBoxseccion;
    private JTextField textFieldtele;
    private JComboBox<String> comboBoxactivo;
    private JButton bcrear;
    private JButton bactualizar;
    private JButton bbuscar;
    private JButton beliminar;
    private JPanel juan;

    private eje2Service userService; // Servicio para manejar la lógica de la base de datos

    // Método necesario para los componentes configurados como "Custom Create"
    private void createUIComponents() {
        // Crear manualmente el JComboBox de secciones con las opciones A, B y C
        comboBoxseccion = new JComboBox<>(new String[]{"A", "B", "C"});

        // Crear manualmente el JComboBox de activo con las opciones "Sí" y "No"
        comboBoxactivo = new JComboBox<>(new String[]{"Sí", "No"});
    }

    // Constructor
    public eje2() {
        // Inicializar la conexión a la base de datos usando DatabaseConnection
        try {
            Connection connection = DatabaseConnection.getConnection();
            userService = new eje2Service(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Listener para el botón "Crear"
        bcrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Recoger los datos del formulario
                    String carne = textFieldcarne.getText();
                    String nombre = textFieldnombre.getText();
                    String correo = textFieldcorreo.getText();
                    String seccion = (String) comboBoxseccion.getSelectedItem();
                    String telegramId = textFieldtele.getText();
                    String activoStr = (String) comboBoxactivo.getSelectedItem();

                    // Verificar si activoStr es null y asignar un valor predeterminado si es necesario
                    boolean activo = "Sí".equals(activoStr);

                    eje2Model newUser = new eje2Model(carne, nombre, correo, seccion, telegramId, activo);
                    userService.createUser(newUser);
                    JOptionPane.showMessageDialog(null, "Usuario creado exitosamente.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al crear el usuario.");
                }
            }
        });

        // Listener para el botón "Buscar"
        bbuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String carne = textFieldcarne.getText();
                    eje2Model user = userService.getUserByCarne(carne);

                    if (user != null) {
                        // Mostrar los datos del usuario en los campos
                        textFieldnombre.setText(user.getNombre());
                        textFieldcorreo.setText(user.getCorreo());
                        comboBoxseccion.setSelectedItem(user.getSeccion());
                        textFieldtele.setText(user.getTelegramId());
                        comboBoxactivo.setSelectedItem(user.isActivo() ? "Sí" : "No");
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al buscar el usuario.");
                }
            }
        });

        // Listener para el botón "Actualizar"
        bactualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String carne = textFieldcarne.getText();
                    String nombre = textFieldnombre.getText();
                    String correo = textFieldcorreo.getText();
                    String seccion = (String) comboBoxseccion.getSelectedItem();
                    String telegramId = textFieldtele.getText();
                    String activoStr = (String) comboBoxactivo.getSelectedItem();

                    // Verificar si activoStr es null y asignar un valor predeterminado si es necesario
                    boolean activo = "Sí".equals(activoStr);

                    eje2Model updatedUser = new eje2Model(carne, nombre, correo, seccion, telegramId, activo);
                    userService.updateUser(updatedUser);
                    JOptionPane.showMessageDialog(null, "Usuario actualizado exitosamente.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al actualizar el usuario.");
                }
            }
        });

        // Listener para el botón "Eliminar"
        beliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String carne = textFieldcarne.getText();
                    userService.deleteUser(carne);
                    JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al eliminar el usuario.");
                }
            }
        });
    }

    // Método para devolver el panel principal
    public JPanel getPanel() {
        return juan;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("eje2.form");
        eje2 form = new eje2();
        frame.setContentPane(form.getPanel()); // Usar el método getPanel() para obtener el panel raíz
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
