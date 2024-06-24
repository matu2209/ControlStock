package SistemaPEMNS;

import Gestor.GestorCollGen;

import Gestor.GestorMapGen;
import Productos.Accesorios;
import Productos.Calzado;
import Productos.Indumentaria;
import Productos.Producto;
import Orden.*;
import enumeradores.DestinoEcommerce;
import enumeradores.Prioridad;
import estanteria.Estanteria;
import estanteria.Posicion;
import estanteria.ProductoAlmacenado;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class SistemaPEMNS {

    private final GestorCollGen<Producto, TreeSet<Producto>,Integer, Prioridad> gestorProductos;
    private final GestorCollGen<OrdenAlmacenamiento, PriorityQueue<OrdenAlmacenamiento>,Integer, String> gestorOrdenesAlmacenamiento;
    private final GestorCollGen<OrdenPicking, PriorityQueue<OrdenPicking>,Integer, String> gestorOrdenesPicking;
    private final GestorCollGen<Estanteria, ArrayList<Estanteria>, Integer, Prioridad> gestorEstanteria;  //vamos a trabajar por busqueda desde esta collection pero estaria bueno trabajarlo desde mapaRelacionalRastreo para optimizar las busquedas
    private final GestorMapGen<Producto, Integer, Prioridad, Posicion, Integer, Double, Map<Producto, Posicion>> mapaRelacionalRastreo; //es quien va a llevar la relacion entre un producto y las ubicaciones donde se encuentra almacenado
    private final GestorMapGen<Posicion, Integer, Double, ProductoAlmacenado, Integer, Producto, Map<Posicion, ProductoAlmacenado>>mapaRelacionalAlmacenamiento;// totalmente necesario para las operaciones de busqueda tanto para almacenamiento como para pickeo



    public SistemaPEMNS() {
        this.gestorProductos = new GestorCollGen<>(new TreeSet<Producto>());
        this.gestorOrdenesAlmacenamiento = new GestorCollGen<>(new PriorityQueue<OrdenAlmacenamiento>());
        this.gestorOrdenesPicking = new GestorCollGen<>(new PriorityQueue<OrdenPicking>());
        this.gestorEstanteria = new GestorCollGen<>(new ArrayList<Estanteria>());
        this.mapaRelacionalRastreo=new GestorMapGen<>(new TreeMap<Producto,Posicion>());
        this.mapaRelacionalAlmacenamiento=new GestorMapGen<>(new TreeMap<Posicion,ProductoAlmacenado>());
    }

    public GestorCollGen<Producto, TreeSet<Producto>, Integer, Prioridad> getGestorProductos() {
        return gestorProductos;
    }

    public GestorMapGen<Producto, Integer, Prioridad, Posicion, Integer, Double, Map<Producto, Posicion>> getMapaRelacionalRastreo() {
        return mapaRelacionalRastreo;
    }

    public GestorCollGen<Estanteria, ArrayList<Estanteria>, Integer, Prioridad> getGestorEstanteria() {
        return gestorEstanteria;
    }

    public GestorCollGen<OrdenPicking, PriorityQueue<OrdenPicking>, Integer, String> getGestorOrdenesPicking() {
        return gestorOrdenesPicking;
    }

    public GestorCollGen<OrdenAlmacenamiento, PriorityQueue<OrdenAlmacenamiento>, Integer, String> getGestorOrdenesAlmacenamiento() {
        return gestorOrdenesAlmacenamiento;
    }

    public GestorMapGen<Posicion, Integer, Double, ProductoAlmacenado, Integer, Producto, Map<Posicion, ProductoAlmacenado>> getMapaRelacionalAlmacenamiento() {
        return mapaRelacionalAlmacenamiento;
    }

    //HAY QUE MODIFICAR LO DE ABAJO PARA QUE NO TIRE ERROR, AHI ME PONGO A HACERLO

//    public boolean generarOrdenPickingDesdePedido (Integer hashProducto, Integer cantidad, String idPedido, DestinoEcommerce destinoEcommerce){
//        List<Posicion> posicionesDelProducto = this.getMapaRelacionalRastreo().buscarPorClaveTodos(hashProducto);
//        Integer cantidadAAlmacenar = cantidad;
//        int posicion = 0;
//        while(cantidadAAlmacenar>0){
//            if(posicionesDelProducto.get(posicion++){
//
//            }
//            new OrdenPicking(hashProducto, cantidad );
//        }
//
//
//
//        return ;
//    }

    public String guardarProductosExel(){
        String path = "src/main/resources/calzados.xlsx";

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Libro Productos");

            // Filas del encabezado
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Codigo");
            headerRow.createCell(1).setCellValue("Marca");
            headerRow.createCell(2).setCellValue("Articulo");
            headerRow.createCell(3).setCellValue("Talle");
            headerRow.createCell(4).setCellValue("Stock");
            headerRow.createCell(5).setCellValue("Volumen");
            headerRow.createCell(6).setCellValue("Prioridad");
            headerRow.createCell(7).setCellValue("Empresa");
            headerRow.createCell(8).setCellValue("Segmento/disciplina");

            int rowNum = 1;

            for (Producto producto : gestorProductos.getElementos()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(producto.getHashProducto());
                row.createCell(1).setCellValue(producto.getMarca());
                row.createCell(2).setCellValue(producto.getArticulo());
                row.createCell(3).setCellValue(producto.getTalle());
                row.createCell(4).setCellValue(producto.getStock());
                row.createCell(5).setCellValue(producto.getVolumen());
                row.createCell(6).setCellValue(producto.getPrioridad().toString());
                row.createCell(7).setCellValue(producto.getEmpresa().toString());
                if(producto instanceof Accesorios) row.createCell(8).setCellValue(((Accesorios) producto).getDisciplina().toString());
                if(producto instanceof Calzado) row.createCell(8).setCellValue(((Calzado) producto).getSegmento().toString());
                if(producto instanceof Indumentaria) row.createCell(8).setCellValue(((Indumentaria) producto).getSegmento().toString());
            }
            for (int i = 0; i < 9; i++) {
                sheet.autoSizeColumn(i);
            }
            try (FileOutputStream fileOut = new FileOutputStream(path)) {
                workbook.write(fileOut);
                return "Libro guardado con exito!";
            }

        } catch (IOException e) {
            return e.getMessage();
        }
    }


    /*public boolean generarOrdenPicking(Integer hashProducto, int cantidad, String idPedido, DestinoEcommerce ecommerce) {
        //buscar el producto

        this.getGestorEstanteria().
        Producto producto = gestorProductos.buscar(hashProducto);
            Estanteria estanteria = gestorEstanteria.buscar(producto.getPrioridad().getValor());
            for (Map.Entry<Posicion, ProductoAlmacenado> entry : estanteria.getEstanteria().entrySet()) {
                if (entry.getValue().getProductoAlmacenado().equals(producto) && entry.getValue().getProductoAlmacenado().getStock() >= cantidad) {
                    producto.setStock(producto.getStock()-cantidad);//se reserva del stock disponible
                    return gestorOrdenesPicking.agregar(new OrdenPicking(producto, cantidad, ecommerce, idPedido,estanteria.getIdEstanteria(),entry.getKey().getHashPosicion()));
                }
            }
        return false;
    }

    public boolean generarOrdenAlmacenamiento(Integer hashProducto, int cantidad, String nroRemito, Empresa empresa){
        //buscar el producto
        Producto producto = gestorProductos.buscar(hashProducto);

        return gestorOrdenesAlmacenamiento.agregar(new OrdenAlmacenamiento(producto,cantidad,nroRemito,empresa));
    }

    public void finalizarOrdenPicking(Integer idOrden, Integer legajoFinalizador){
        OrdenPicking ordenPicking = gestorOrdenesPicking.buscar(idOrden);
        //sacar que sea una linea de codigo
        gestorEstanteria.buscar(ordenPicking.getEstanteria()).getEstanteria().get(ordenPicking.getPosicion()).setVolumenAlmacenado(ordenPicking.getProducto().getVolumen()*(ordenPicking.getCantidadProducto())*-1);
        ordenPicking.finalizarOrden(legajoFinalizador,ordenPicking.getPosicion());
    }



     */
}
