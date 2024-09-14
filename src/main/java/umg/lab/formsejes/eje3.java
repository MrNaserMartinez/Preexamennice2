package umg.lab.formsejes;

import umg.lab.DataBase.Model.eje3Model;
import umg.lab.DataBase.Services.eje3Service;
import umg.lab.DataBaseConeccion.DatabaseConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class eje3 {
    private JLabel lbl2;
    private JLabel lbl3;
    private JLabel lbl4;
    private JLabel lbl5;
    private JLabel lbl6;
    private JLabel lbl7;
    private JLabel lbl8;
    private JLabel lbl9;
    private JLabel lbl10;
    private JLabel lbl11;
    private JLabel lbl12;
    private JLabel lbl13;
    private JLabel lbl14;
    private JTextField textFieldid;
    private JTextField textFieldnombre;
    private JTextField textFieldpais;
    private JTextField textFieldciudad;
    private JTextField textFieldestadio;
    private JTextField textFieldfundacion;
    private JTextField textFieldentrenador;
    private JTextField textFieldweb;
    private JTextField textFieldface;
    private JTextField textFieldtwitter;
    private JTextField textFieldig;
    private JTextField textFieldpatro;
    private JTextField textFieldcreado;
    private JButton bcrear;
    private JButton bactualizar;
    private JButton bbuscar;
    private JButton beliminar;
    private JPanel eje3ni;

    private eje3Service equipoService;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public eje3() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            equipoService = new eje3Service(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos.");
        }

        bcrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Recoger los datos del formulario
                    String nombre = textFieldnombre.getText();
                    String pais = textFieldpais.getText();
                    String ciudad = textFieldciudad.getText();
                    String estadio = textFieldestadio.getText();
                    int fundacion = Integer.parseInt(textFieldfundacion.getText());
                    String entrenador = textFieldentrenador.getText();
                    String webOficial = textFieldweb.getText();
                    String facebook = textFieldface.getText();
                    String twitter = textFieldtwitter.getText();
                    String instagram = textFieldig.getText();
                    String patrocinadorPrincipal = textFieldpatro.getText();

                    // Convertir la fecha al formato dd/MM/yyyy
                    Date fechaCreado = dateFormat.parse(textFieldcreado.getText());
                    Timestamp creadoEn = new Timestamp(fechaCreado.getTime());

                    // Crear el nuevo equipo
                    eje3Model nuevoEquipo = new eje3Model(0, nombre, pais, ciudad, estadio, fundacion, entrenador,
                            webOficial, facebook, twitter, instagram, patrocinadorPrincipal, creadoEn);
                    equipoService.createEquipo(nuevoEquipo);
                    JOptionPane.showMessageDialog(null, "Equipo creado exitosamente.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al crear el equipo.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error en los datos del formulario. Asegúrate de ingresar números en los campos correspondientes.");
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto. Asegúrate de usar el formato dd/MM/yyyy.");
                }
            }
        });

        bbuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(textFieldid.getText());
                    eje3Model equipo = equipoService.getEquipoById(id);

                    if (equipo != null) {
                        // Mostrar los datos del equipo en los campos
                        textFieldnombre.setText(equipo.getNombre());
                        textFieldpais.setText(equipo.getPais());
                        textFieldciudad.setText(equipo.getCiudad());
                        textFieldestadio.setText(equipo.getEstadio());
                        textFieldfundacion.setText(String.valueOf(equipo.getFundacion()));
                        textFieldentrenador.setText(equipo.getEntrenador());
                        textFieldweb.setText(equipo.getWebOficial());
                        textFieldface.setText(equipo.getFacebook());
                        textFieldtwitter.setText(equipo.getTwitter());
                        textFieldig.setText(equipo.getInstagram());
                        textFieldpatro.setText(equipo.getPatrocinadorPrincipal());

                        // Convertir la fecha al formato dd/MM/yyyy
                        String fechaCreado = dateFormat.format(equipo.getCreadoEn());
                        textFieldcreado.setText(fechaCreado);
                    } else {
                        JOptionPane.showMessageDialog(null, "Equipo no encontrado.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al buscar el equipo.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID debe ser un número entero.");
                }
            }
        });

        bactualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(textFieldid.getText());
                    String nombre = textFieldnombre.getText();
                    String pais = textFieldpais.getText();
                    String ciudad = textFieldciudad.getText();
                    String estadio = textFieldestadio.getText();
                    int fundacion = Integer.parseInt(textFieldfundacion.getText());
                    String entrenador = textFieldentrenador.getText();
                    String webOficial = textFieldweb.getText();
                    String facebook = textFieldface.getText();
                    String twitter = textFieldtwitter.getText();
                    String instagram = textFieldig.getText();
                    String patrocinadorPrincipal = textFieldpatro.getText();

                    // Convertir la fecha al formato dd/MM/yyyy
                    Date fechaCreado = dateFormat.parse(textFieldcreado.getText());
                    Timestamp creadoEn = new Timestamp(fechaCreado.getTime());

                    eje3Model equipoActualizado = new eje3Model(id, nombre, pais, ciudad, estadio, fundacion, entrenador,
                            webOficial, facebook, twitter, instagram, patrocinadorPrincipal, creadoEn);
                    equipoService.updateEquipo(equipoActualizado);
                    JOptionPane.showMessageDialog(null, "Equipo actualizado exitosamente.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al actualizar el equipo.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error en los datos del formulario. Asegúrate de ingresar números en los campos correspondientes.");
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto. Asegúrate de usar el formato dd/MM/yyyy.");
                }
            }
        });

        beliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(textFieldid.getText());
                    equipoService.deleteEquipo(id);
                    JOptionPane.showMessageDialog(null, "Equipo eliminado exitosamente.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al eliminar el equipo.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID debe ser un número entero.");
                }
            }
        });
    }

    public JPanel getPanel() {
        return eje3ni;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("eje3.form");
        eje3 form = new eje3();
        frame.setContentPane(form.getPanel()); // Usar el método getPanel() para obtener el panel raíz
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
