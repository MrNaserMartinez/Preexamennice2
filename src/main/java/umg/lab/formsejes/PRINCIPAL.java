package umg.lab.formsejes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PRINCIPAL {
    private JLabel lbl1;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JPanel principalPanel;

    public PRINCIPAL() {
        // Acción del botón b1 para abrir el formulario eje1
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("eje1");
                eje1 formEje1 = new eje1();
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
                eje2 formEje2 = new eje2();
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
                eje3 formEje3 = new eje3();
                frame.setContentPane(formEje3.getPanel());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public JPanel getPanel() {
        return principalPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("PRINCIPAL");
        PRINCIPAL principalForm = new PRINCIPAL();
        frame.setContentPane(principalForm.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}