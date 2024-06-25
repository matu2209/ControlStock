package org.example;

import Productos.Accesorios;
import Productos.Calzado;
import Productos.Indumentaria;
import Productos.Producto;
import SistemaPEMNS.SistemaPEMNS;
import enumeradores.Disciplina;
import enumeradores.Empresa;
import enumeradores.Prioridad;
import enumeradores.Segmento;
import estanteria.Estanteria;
import estanteria.EstanteriaCalzado;
import estanteria.EstanteriaIndAcc;
import estanteria.Posicion;

import java.util.Map;

public class Main {
    public static void main(String[] args) {

//

//        System.out.println("Estanteria de accesorios");
//        for (Map.Entry<Posicion, ProductoAlmacenado> entry : estanteriaAccesorios.getEstanteria().entrySet()){
//            System.out.println("Clave posicion accesorios: " + entry.getKey());
//        }
//        System.out.println("Estanteria de calzado");
//        for (Map.Entry<Posicion, ProductoAlmacenado> entry : estanteriaZapatillas.getEstanteria().entrySet()){
//            System.out.println("Clave posicion calzado: " + entry.getKey());
//        }

//        Producto nike = new Calzado("Nike","Air Force",42,10,1.00,Prioridad.ALTA, Segmento.ADULTOS);//el volumen deberia ser una caja que ocupa 1% de posiscion
//        Producto adidas = new Calzado("Adidas","response cl",40,20,1.00,Prioridad.ALTA, Segmento.ADULTOS);
//
//        for (Map.Entry<Posicion, ProductoAlmacenado> entry : estanteriaZapatillas.getEstanteria().entrySet()){
//            if (entry.getKey().getVolumenDisponible().getCapacidad() > (nike.getVolumen() * nike.getStock())){
//                entry.setValue(new ProductoAlmacenado(nike.getHashProducto(),nike.getStock(),nike.getVolumen()));
//                entry.getKey().setVolumenDisponible(VolumenDisponible.MEDIO);
//                break;
//            }
//        }
//
//        for (Map.Entry<Posicion, ProductoAlmacenado> entry : estanteriaZapatillas.getEstanteria().entrySet()){
//            if (entry.getKey().getVolumenDisponible().getCapacidad() > (adidas.getVolumen() * adidas.getStock())){
//                entry.setValue(new ProductoAlmacenado(adidas.getHashProducto(),adidas.getStock(),adidas.getVolumen()));
//                entry.getKey().setVolumenDisponible(VolumenDisponible.MEDIO);
//                break;
//            }
//        }
//
//        for (Map.Entry<Posicion, ProductoAlmacenado> entry : estanteriaZapatillas.getEstanteria().entrySet()){
//            System.out.println("Clave posicion calzado: " + entry.getKey() + " - " + entry.getValue());
//        }



        Calzado n1 = new Calzado("nike", "air jordan gratitude", 44, 24, 1.0, Prioridad.MEDIA, Segmento.ADULTOS, Empresa.ALMACEN_ADIDAS);
        Calzado n2 = new Calzado("nike", "sb dunk low pro", 42, 30, 1.0, Prioridad.ALTA, Segmento.ADULTOS,Empresa.ALMACEN_ADIDAS);
        Calzado n3 = new Calzado("nike", "cortez", 41, 15, 1.0, Prioridad.MEDIA, Segmento.ADULTOS,Empresa.ALMACEN_ADIDAS);
        Calzado n4 = new Calzado("nike", "run swift 3", 39, 40, 1.0, Prioridad.ALTA, Segmento.ADULTOS,Empresa.LIBERTAD);
        Calzado n5 = new Calzado("nike", "pegasus 39 shield", 46, 10, 1.0, Prioridad.BAJA, Segmento.ADULTOS,Empresa.ALMACEN_ADIDAS);
        Calzado n6 = new Calzado("nike", "le bron witness 8", 42, 50, 1.0, Prioridad.ALTA, Segmento.ADULTOS,Empresa.LIBERTAD);
        Calzado n7 = new Calzado("nike", "lunargato 2", 40, 20, 1.0, Prioridad.MEDIA, Segmento.ADULTOS,Empresa.LIBERTAD);
        Calzado n8 = new Calzado("nike", "gt jumpo 2", 44, 14, 1.0, Prioridad.MEDIA, Segmento.ADULTOS,Empresa.LIBERTAD);
        Accesorios a1 = new Accesorios("adidas","pelota nro 5 epp club",1,30,2.0,Prioridad.ALTA, Disciplina.DEPORTE,Empresa.ALMACEN_ADIDAS);
        Accesorios a2 = new Accesorios("puma","pelota nro 5 icon",1,40,2.0,Prioridad.ALTA, Disciplina.DEPORTE,Empresa.LIBERTAD);
        Accesorios a3 = new Accesorios("rip curl","mochila dome bts5 dama",1,15,3.0,Prioridad.ALTA, Disciplina.SENDERISMO,Empresa.ECOMMERCE);
        Accesorios a4 = new Accesorios("speed","antiparras namazu",1,20,1.0,Prioridad.MEDIA, Disciplina.NATACION,Empresa.ALMACEN_ADIDAS);
        Indumentaria i1 = new Indumentaria("new balance","calza accelerate color block",3,20,5.0,Prioridad.ALTA,Segmento.ADULTOS,Empresa.LIBERTAD);
        Indumentaria i2 = new Indumentaria("new balance","buzo essentials",2,25,5.0,Prioridad.ALTA,Segmento.ADULTOS,Empresa.LIBERTAD);
        Indumentaria i4 = new Indumentaria("speed","musculosa c-recortes",1,30,5.0,Prioridad.ALTA,Segmento.ADULTOS,Empresa.LIBERTAD);
        SistemaPEMNS sistema = new SistemaPEMNS();
//        sistema.getGestorProductos().agregar(a1);
//        sistema.getGestorProductos().agregar(a2);
//        sistema.getGestorProductos().agregar(a3);
//        sistema.getGestorProductos().agregar(a4);
//        sistema.getGestorProductos().agregar(n1);
//        sistema.getGestorProductos().agregar(n2);
//        sistema.getGestorProductos().agregar(n3);
//        sistema.getGestorProductos().agregar(n4);
//        sistema.getGestorProductos().agregar(n5);
//        sistema.getGestorProductos().agregar(n6);
//        sistema.getGestorProductos().agregar(n7);
//        sistema.getGestorProductos().agregar(n8);
//        sistema.getGestorProductos().agregar(i1);
//        sistema.getGestorProductos().agregar(i2);
//        sistema.getGestorProductos().agregar(i4);
        //sistema.getGestorProductos().getElementos().forEach(System.out::println);

        //sistema.guardarProductosExel();
        Estanteria e1 = new EstanteriaCalzado(Prioridad.ALTA,Empresa.LIBERTAD);
        e1.crearPosicionesEstanteria();
        Estanteria e2 = new EstanteriaIndAcc(Prioridad.MEDIA,Empresa.ALMACEN_ADIDAS);
        e2.crearPosicionesEstanteria();
        //sistema.leerProductosExel();
        sistema.getGestorEstanteria().agregar(e1);
        sistema.getGestorEstanteria().agregar(e2);
        sistema.getMapaRelacionalRastreo().agregar(n1,sistema.getGestorEstanteria().getElementos().get(1).getListaPosiciones().getElementos().get(1));
        sistema.getMapaRelacionalRastreo().agregar(n2,sistema.getGestorEstanteria().getElementos().get(1).getListaPosiciones().getElementos().get(2));
        sistema.getMapaRelacionalRastreo().agregar(n3,sistema.getGestorEstanteria().getElementos().get(1).getListaPosiciones().getElementos().get(3));
        sistema.getMapaRelacionalRastreo().agregar(n4,sistema.getGestorEstanteria().getElementos().get(1).getListaPosiciones().getElementos().get(4));
//        System.out.println(sistema.getMapaRelacionalRastreo().valores());

        for (Map.Entry<Producto, Posicion> entry : sistema.getMapaRelacionalRastreo().entradas()) {
            System.out.println("Producto" + entry.getKey() + " Posicion " + entry.getValue());
        }

//        try {
//            sistema.guardarMapaRelacionalRastreo();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.out.println("Hello world!");
    }
}