package SistemaPEMNS;

import enumeradores.*;
import gestores.GestorCollGen;

import gestores.GestorMapGen;
import Productos.Accesorios;
import Productos.Calzado;
import Productos.Indumentaria;
import Productos.Producto;
import Orden.*;
import estanteria.Estanteria;
import estanteria.Posicion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import productoAlmacenado.ProductoAlmacenado;

import java.io.*;
import java.util.*;

public class SistemaPEMNS {
    //GESTORES MANEJADORES DE CLASES ESPECIFICOS DE NUESTRO SISTEMA
    private final GestorCollGen<Producto, TreeSet<Producto>,Integer, Prioridad> gestorProductos;
    private final GestorCollGen<OrdenAlmacenamiento, PriorityQueue<OrdenAlmacenamiento>,Integer, String> gestorOrdenesAlmacenamiento;
    private final GestorCollGen<OrdenPicking, PriorityQueue<OrdenPicking>,Integer, String> gestorOrdenesPicking;
    private final GestorCollGen<Estanteria, ArrayList<Estanteria>, Integer, Prioridad> gestorEstanteria;  //vamos a trabajar por busqueda desde esta collection pero estaria bueno trabajarlo desde mapaRelacionalRastreo para optimizar las busquedas

    //GESTORES RELACIONALES DE BÚSQUEDA DE NUESTRO SISTEMA

    private final GestorMapGen<Producto, Integer, Prioridad, Posicion, Integer, Double, Map<Producto, Posicion>> mapaRelacionalRastreo; //es quien va a llevar la relacion entre un producto y las ubicaciones donde se encuentra almacenado
    // totalmente necesario para las operaciones de busqueda tanto para almacenamiento como para pickeo
    private final GestorMapGen<Posicion, Integer, Double, ProductoAlmacenado, Integer, Integer, Map<Posicion, ProductoAlmacenado>>mapaRelacionalAlmacenamiento;


    public SistemaPEMNS() {
        this.gestorProductos = new GestorCollGen<>(new TreeSet<>());
        this.gestorOrdenesAlmacenamiento = new GestorCollGen<>(new PriorityQueue<OrdenAlmacenamiento>());
        this.gestorOrdenesPicking = new GestorCollGen<>(new PriorityQueue<OrdenPicking>());
        this.gestorEstanteria = new GestorCollGen<>(new ArrayList<>());
        this.mapaRelacionalRastreo=new GestorMapGen<>(new TreeMap<>());
        this.mapaRelacionalAlmacenamiento=new GestorMapGen<>(new TreeMap<>());
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

    public GestorMapGen<Posicion, Integer, Double, ProductoAlmacenado, Integer, Integer, Map<Posicion, ProductoAlmacenado>> getMapaRelacionalAlmacenamiento() {
        return mapaRelacionalAlmacenamiento;
    }


    public int generarOrdenPickingDesdePedido (Integer hashProducto, Integer cantidad, DestinoEcommerce destinoEcommerce, String idPedido) {
        List<Posicion> posicionesDelProducto = this.getMapaRelacionalRastreo().buscarPorClaveTodos(hashProducto); // Obtengo la lista de posiciones en que se almacena un producto
        Integer cantidadAPickear = cantidad;
        int indexPosicion = 0;

        while (cantidadAPickear > 0 && indexPosicion < posicionesDelProducto.size()) { //mientras haya que pickear y mientras que el indice de posicion sea menor al tamaño de la lista filtrada de posiciones
            List<ProductoAlmacenado> productosBuscados = this.getMapaRelacionalAlmacenamiento()
                    .buscarPorClaveTodos(posicionesDelProducto.get(indexPosicion).getHashPosicion())
                    .stream()
                    .filter(elemento -> elemento.getProductoAlmacenado().getHashProducto().equals(hashProducto))
                    .toList(); // Obtengo la lista de productos almacenados en una posición y los filtro por el hash del producto que necesito

            for (ProductoAlmacenado productoAlmacenado : productosBuscados) {
                if (productoAlmacenado.getStockDePosicion() >= cantidadAPickear) {
                    getGestorOrdenesPicking().agregar(new OrdenPicking(hashProducto, cantidadAPickear, destinoEcommerce, idPedido, posicionesDelProducto.get(indexPosicion).getHashPosicion()));
                    cantidadAPickear = 0;
                    break;
                } else {
                    getGestorOrdenesPicking().agregar(new OrdenPicking(hashProducto, productoAlmacenado.getStockDePosicion(), destinoEcommerce, idPedido, posicionesDelProducto.get(indexPosicion).getHashPosicion()));
                    cantidadAPickear -= productoAlmacenado.getStockDePosicion();
                }
            }

            indexPosicion++;
        }

        if (cantidadAPickear > 0) {
            // No se pudo completar el picking de todos los productos solicitados
            throw new FaltaDeStockException("No se puede generar la orden de pickeo del producto " + hashProducto + " por falta de stock");
        }

        return cantidad - cantidadAPickear; // Retorna la cantidad que se logró asignar para pickear
    }


    public int generarOrdenAlmacenamientoDesdeRemito(Integer hashProducto, Integer cantidad, Empresa empresa, String nroRemito) {
        Producto productoAAlmacenar = getGestorProductos().buscarPrimero(hashProducto); // Busco el objeto producto a almacenar en el gestor de productos para obtener los atributos del mismo
        int cantidadAAlmacenar = cantidad;
        int indexPosicion = 0;

        // Filtra la lista de posiciones por aquellas que tienen un volumen disponible mayor o igual al del producto que va a almacenar
        // y luego las vuelve a filtrar por aquellas que matchean por la prioridad de nuestro producto
        List<Posicion> posicionesLibres = this.getMapaRelacionalAlmacenamiento()
                .filtrarClaves(productoAAlmacenar.getVolumen())
                .stream()
                .filter(element -> element.filtrarPorPrioridad(productoAAlmacenar.getPrioridad()))
                .toList();

        while (cantidadAAlmacenar > 0 && indexPosicion < posicionesLibres.size()) {
            Posicion posicion = posicionesLibres.get(indexPosicion);
            double volumenRequerido = productoAAlmacenar.getVolumen() * cantidadAAlmacenar;

            if (volumenRequerido <= posicion.getVolumenDisponible()) {
                getGestorOrdenesAlmacenamiento().agregar(new OrdenAlmacenamiento(hashProducto, cantidadAAlmacenar, nroRemito, empresa, posicion.getHashPosicion()));
                cantidadAAlmacenar = 0; //El producto fue almacenado por completo
                break;
            } else {
                int cantidadPorPosicion = (int) Math.floor(posicion.getVolumenDisponible() / productoAAlmacenar.getVolumen());
                getGestorOrdenesAlmacenamiento().agregar(new OrdenAlmacenamiento(hashProducto, cantidadPorPosicion, nroRemito, empresa, posicion.getHashPosicion()));
                cantidadAAlmacenar -= cantidadPorPosicion;
            }

            indexPosicion++;
        }

        if (cantidadAAlmacenar > 0) {
            //si no se pudo almacenar todo el producto, lanzo una excepcion
            throw new FaltaDeEspacioException("No se puede almacenar el producto " + hashProducto +  " por falta de espacio en la estanteria");
        }

        return cantidad - cantidadAAlmacenar; // Retorna la cantidad que se logró asignar para almacenar
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

    public String leerProductosExel() {
        String path = "src/main/resources/productos.xlsx";
        File archivo = new File(path);

        try {

            InputStream input = new FileInputStream(archivo);
            XSSFWorkbook workbook = new XSSFWorkbook(input);
            XSSFSheet sheet = workbook.getSheetAt(0);


            Iterator<Row> filas = sheet.rowIterator();
            Iterator<Cell> columnas = null;
            Integer hashProducto = 0, talle = 0, stock = 0;
            Double volumen = 0.0;
            Prioridad prioridad = null;
            Empresa empresa = null;
            String marca = "", articulo = "";
            Segmento segmento;
            Disciplina disciplina = null;

            Row filaActual = null;
            Cell columnaActual = null;
            filas.next();
            while (filas.hasNext()) {
                filaActual = filas.next();
                columnas = filaActual.cellIterator();
                Iterator<Cell> encabezado = sheet.getRow(0).cellIterator();
                Cell columnaEncabezado = null;

                while (columnas.hasNext()) {
                    columnaActual = columnas.next();
                    columnaEncabezado = encabezado.next();

                    //aca uso encabezado para ver donde estoy parado
                    //las filas corresponden
//                    if (columnaEncabezado.getCellType() == CellType.NUMERIC) {
//                        //hashProducto = (int) cell.getNumericCellValue();
//                        System.out.println(columnaEncabezado.getNumericCellValue());
//                    }
//                    if (columnaEncabezado.getCellType() == CellType.STRING) {
//                        System.out.println(columnaEncabezado.getStringCellValue());
//                    }
                    if (columnaEncabezado.getStringCellValue().equals("Codigo")) {
                        hashProducto = (int) columnaActual.getNumericCellValue();
                    }
                    if (columnaEncabezado.getStringCellValue().equals("Marca")) {
                        marca = columnaActual.getStringCellValue();
                    }
                    if (columnaEncabezado.getStringCellValue().equals("Articulo")) {
                        articulo = columnaActual.getStringCellValue();
                    }
                    if (columnaEncabezado.getStringCellValue().equals("Talle")) {
                        talle = (int) columnaActual.getNumericCellValue();
                    }
                    if (columnaEncabezado.getStringCellValue().equals("Stock")) {
                        stock = (int) columnaActual.getNumericCellValue();
                    }
                    if (columnaEncabezado.getStringCellValue().equals("Volumen")) {
                        volumen = columnaActual.getNumericCellValue();
                    }
                    if (columnaEncabezado.getStringCellValue().equals("Prioridad")) {
                        prioridad = Prioridad.valueOf(columnaActual.getStringCellValue());
                    }
                    if (columnaEncabezado.getStringCellValue().equals("Empresa")) {
                        empresa = Empresa.valueOf(columnaActual.getStringCellValue());
                    }
                    if (columnaEncabezado.getStringCellValue().equals("Segmento/disciplina")) {
                        if ((columnaActual.getStringCellValue()).equals("ADULTOS") || (columnaActual.getStringCellValue()).equals("NINIOS")) {
                            segmento = Segmento.valueOf(columnaActual.getStringCellValue());
                            if (talle > 25) {
                                gestorProductos.agregar(new Calzado(hashProducto, marca, articulo, talle, stock, volumen, prioridad, segmento, empresa));
                            } else {
                                gestorProductos.agregar(new Indumentaria(hashProducto, marca, articulo, talle, stock, volumen, prioridad, segmento, empresa));
                            }
                        } else {
                            disciplina = Disciplina.valueOf(columnaActual.getStringCellValue());
                            gestorProductos.agregar(new Accesorios(hashProducto, marca, articulo, talle, stock, volumen, prioridad, disciplina, empresa));
                        }
                    }
                }

            }
            input.close();
            workbook.close();
            return "bien capo";
        } catch (Exception e) {
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
