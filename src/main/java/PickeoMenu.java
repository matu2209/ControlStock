import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class PickeoMenu extends JFrame {
    private JTextField titlePickeoMenu;
    private JButton obtenerOrdenButton;
    private JPanel pickeoPanel;
    private JButton entregarOrdenButton;
    private JButton volverButton;

    public PickeoMenu () {
        setContentPane(pickeoPanel);
        setTitle("Menú Pickeo");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Acción para el botón de obtener orden de pickeo
        obtenerOrdenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obtenerOrdenDePickeo();
            }
        });

        // Acción para el botón de entregar orden de pickeo
        entregarOrdenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entregarOrdenDePickeo();
            }
        });

        // Acción para volver al menú principal
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LandingPage();
            }
        });

        setVisible(true);
    }

    private void obtenerOrdenDePickeo() {
        // Implementar la obtención de la orden de pickeo
        JOptionPane.showMessageDialog(this, "Obtener Orden de Pickeo");
    }

    private void entregarOrdenDePickeo() {
        // Implementar la entrega de la orden de pickeo
        JOptionPane.showMessageDialog(this, "Entregar Orden de Pickeo");
    }

}
