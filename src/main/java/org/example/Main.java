package org.example;

import Productos.Calzado;
import Productos.Producto;
import enumeradores.MedidasCalzado;
import enumeradores.Prioridad;
import enumeradores.Segmento;
import enumeradores.VolumenDisponible;
import estanteria.*;

import java.util.ArrayList;
import java.util.List;
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
        System.out.println("Hello world!");
    }
}