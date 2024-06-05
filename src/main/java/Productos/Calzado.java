package Productos;

import enumeradores.Prioridad;
import enumeradores.Segmento;

public class Calzado extends Producto {
    private Segmento segmento;

    public Calzado(String marca, String articulo, Integer talle, Integer stock, Double volumen, Prioridad prioridad, Segmento segmento) {
        super(marca, articulo, talle, stock, volumen, prioridad);
        this.segmento = segmento;
    }

    public Segmento getSegmento() {
        return segmento;
    }

    public void setSegmento(Segmento segmento) {
        this.segmento = segmento;
    }

}
