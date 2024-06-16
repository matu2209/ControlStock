import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ItemsMenu extends JFrame {
    private JPanel itemsPanel;
    private JButton agregarStockButton;
    private JButton buscarStockButton;
    private JButton verDetallesButton;
    private JButton volverButton;
    private JLabel itemsMenuTitle;

    public ItemsMenu() {
        setContentPane(itemsPanel);
        setTitle("Menú Items");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Acción para el botón de agregar stock o item
        agregarStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarStock();
            }
        });

        // Acción para el botón de buscar stock o item
        buscarStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarStock();
            }
        });

        // Acción para el botón de ver detalles de item
        verDetallesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verDetalles();
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

    private void agregarStock() {
        // Implementar la funcionalidad para agregar stock o item
        JOptionPane.showMessageDialog(this, "Agregar Stock o Item");
    }

    private void buscarStock() {
        // Implementar la funcionalidad para buscar stock o item
        JOptionPane.showMessageDialog(this, "Buscar Stock o Item");
    }

    private void verDetalles() {
        // Implementar la funcionalidad para ver detalles de item
        JOptionPane.showMessageDialog(this, "Ver Detalles de Item");
    }
}


