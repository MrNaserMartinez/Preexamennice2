package umg.lab.formsejes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PRINCIPAL {
    private JLabel lbl1;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JPanel principalPanel;

    public PRINCIPAL() {
        // Inicializa el panel principal
        principalPanel = new JPanel();
        principalPanel.setLayout(new FlowLayout()); // Usa el diseño que prefieras

        // Inicializa los botones
        b1 = new JButton("Abrir eje1");
        b2 = new JButton("Abrir eje2");
        b3 = new JButton("Abrir eje3");

        // Añade los botones al panel
        principalPanel.add(b1);
        principalPanel.add(b2);
        principalPanel.add(b3);

        // Acción del botón b1 para abrir el formulario eje1
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("eje1");
                eje1 formEje1 = new eje1(); // Asegúrate de que existe la clase eje1 con el método getPanel()
                frame.setContentPane(formEje1.getPanel());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        // Acción del botón b2 para abrir el formulario eje2
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("eje2");
                eje2 formEje2 = new eje2(); // Asegúrate de que existe la clase eje2 con el método getPanel()
                frame.setContentPane(formEje2.getPanel());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        // Acción del botón b3 para abrir el formulario eje3
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("eje3");
                eje3 formEje3 = new eje3(); // Asegúrate de que existe la clase eje3 con el método getPanel()
                frame.setContentPane(formEje3.getPanel());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public JPanel getPanel() {
        return principalPanel; // Devolver el panel principal que contiene los botones
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("PRINCIPAL");
        PRINCIPAL principalForm = new PRINCIPAL(); // Crear una instancia del formulario principal
        frame.setContentPane(principalForm.getPanel()); // Establecer el panel principal en la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la aplicación al hacer clic en cerrar
        frame.pack(); // Ajustar el tamaño de la ventana
        frame.setVisible(true); // Hacer visible la ventana
    }
}
