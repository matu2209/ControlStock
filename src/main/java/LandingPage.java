import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingPage extends JFrame {
    private JPanel mainMenu;
    private JTextField mainTitulo;
    private JButton pickeoMainMenuButton;
    private JButton salirDelSistemaButton;
    private JButton itemsMainMenuButton;
    private JLabel menuTitulo;

    public LandingPage() {
        setContentPane(mainMenu);
        setTitle("Sistema de Stock");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configurar título principal (No editable)
        mainTitulo.setText("Sistema de Stock");
        mainTitulo.setEditable(false);

        // Acción para el botón de salir del sistema
        salirDelSistemaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Cerrar la aplicación
            }
        });

        // Acción para el botón del menú de Pickeo
        pickeoMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              openPickeoMenu();
            }
        });

        // Acción para el botón del menú de Items
        itemsMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               openItemsMenu();
            }
        });

        setVisible(true);
    }
    private void openPickeoMenu() {
        // Implementar la apertura del menú de Pickeo
        JOptionPane.showMessageDialog(this, "Abrir Menú Pickeo");
    }

    private void openItemsMenu() {
        // Implementar la apertura del menú de Items
        JOptionPane.showMessageDialog(this, "Abrir Menú Items");
    }

    public static void main(String[] args) {
        new LandingPage();
    }
}

