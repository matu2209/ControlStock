import SistemaPEMNS.SistemaPEMNS;
import enumeradores.Empresa;

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

    private SistemaPEMNS sistema;

    public ItemsMenu() {
        sistema=new SistemaPEMNS();

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
// Implementar la funcionalidad del sistema PEMNS, cubre posible errores de tipo, lo unico habria que traer la funcion para obtener el hash automaticamente
//Esta asi para poder trabajar y que quede claro
        try {
            String hashProductoStr = JOptionPane.showInputDialog(this, "Ingrese el hash del Producto:");
            String cantidadStr = JOptionPane.showInputDialog(this, "Ingrese la cantidad:");
            String nroRemito = JOptionPane.showInputDialog(this, "Ingrese el número de remito:");
            String empresaStr = JOptionPane.showInputDialog(this, "Ingrese el nombre de la empresa:");

            if (hashProductoStr != null && cantidadStr != null && nroRemito != null && empresaStr != null) {
                Integer hashProducto = Integer.parseInt(hashProductoStr);
                int cantidad = Integer.parseInt(cantidadStr);
                //Si va string se mete por constructor que reciba string
                Empresa empresa;
                try {
                    empresa = Empresa.valueOf(empresaStr.toUpperCase());
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(this, "Empresa no válida. Debe ser una de: LIBERTAD, ECOMMERCE, ALMACEN_ADIDAS.");
                    return;
                }

                boolean resultado = sistema.generarOrdenAlmacenamiento(hashProducto, cantidad, nroRemito, empresa);

                if (resultado) {
                    JOptionPane.showMessageDialog(this, "Stock agregado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al agregar stock.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Formato de número incorrecto.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error: " + e.getMessage());
        }
    }
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


