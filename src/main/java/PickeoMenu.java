import gestores.GestorGenerico;
import Productos.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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

    private void obtenerOrdenDePickeo(Producto producto) {

        String[] columnNames = {"Ubicación", "Marca", "Nombre Artículo", "Talle", "Cantidad/Stock"};
        DefaultTableModel modeloLista = new DefaultTableModel(columnNames, 0);
        /*
        for(){
            Object[] row = {producto.getHashProducto(), producto.getMarca(), producto.getArticulo(), producto.getTalle(), producto.getStock()};
            model.addRow(row);
        }

        */
        JTable productTable = new JTable(modeloLista);//CREA LA TABLA A PARTIR DEL DAFAULT
        JScrollPane scrollPane = new JScrollPane(productTable);//PERMITE EL DESPLAZAMIENTO
        scrollPane.setPreferredSize(new Dimension(480, 290));

        JOptionPane.showMessageDialog(this, scrollPane, "Lista de Productos", JOptionPane.PLAIN_MESSAGE);
    }

    private void entregarOrdenDePickeo() {
        // Implementar la entrega de la orden de pickeo
        JOptionPane.showMessageDialog(this, "Entregar Orden de Pickeo");
    }

}
